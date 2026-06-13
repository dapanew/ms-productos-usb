import http from 'k6/http';
import { check, sleep } from 'k6';
import { randomIntBetween } from 'https://jslib.k6.io/k6-utils/1.2.0/index.js';

// Configuración de la prueba
export const options = {
  stages: [
    { duration: '30s', target: 10 }, // Incremento gradual a 10 usuarios en 30 segundos
    { duration: '1m', target: 10 }, // Mantener 10 usuarios durante 1 minuto
    { duration: '30s', target: 50 }, // Incremento a 50 usuarios en 30 segundos
    { duration: '2m', target: 50 }, // Mantener 50 usuarios durante 2 minutos
    { duration: '30s', target: 0 }, // Reducción gradual a 0 usuarios en 30 segundos
  ],
  thresholds: {
    http_req_duration: ['p(95)<500'], // 95% de las solicitudes deben completarse en menos de 500ms
    http_req_failed: ['rate<0.01'], // Menos del 1% de las solicitudes pueden fallar
  },
};

// Variables de configuración
const BASE_URL = 'http://localhost:8081';
const MIN_ID = 1;
const MAX_ID = 100; // Ajusta según el rango de IDs de facturas existentes

// Función principal que ejecuta k6
export default function() {
  // Generar un ID aleatorio para cada solicitud dentro del rango definido
  const facturaId = randomIntBetween(MIN_ID, MAX_ID);
  
  // Realizar la solicitud GET para obtener una factura por ID
  const response = http.get(`${BASE_URL}/api/facturas/${facturaId}`);
  
  // Verificar que la respuesta sea exitosa (código 200)
  check(response, {
    'status is 200': (r) => r.status === 200,
    'has correct content-type': (r) => r.headers['Content-Type'] && r.headers['Content-Type'].includes('application/json'),
    'response body has factura data': (r) => {
      const body = JSON.parse(r.body);
      return body && body.id === facturaId;
    },
  });
  
  // Registrar métricas personalizadas
  if (response.status === 200) {
    const responseTime = response.timings.duration;
    console.log(`Factura ID: ${facturaId}, Tiempo de respuesta: ${responseTime}ms`);
  } else {
    console.error(`Error al consultar factura ID: ${facturaId}, Estado: ${response.status}`);
  }
  
  // Pausa entre solicitudes para simular comportamiento de usuario real
  sleep(randomIntBetween(1, 3));
}

// Función opcional para simular acceso a facturas específicas con mayor frecuencia
export function getSpecificFacturas() {
  // IDs de facturas de "alta prioridad" o más consultadas
  const highPriorityIds = [1, 5, 10];
  const facturaId = highPriorityIds[randomIntBetween(0, highPriorityIds.length - 1)];
  
  const response = http.get(`${BASE_URL}/api/facturas/${facturaId}`);
  
  check(response, {
    'priority factura status is 200': (r) => r.status === 200,
  });
  
  sleep(1);
}

// Función de configuración que se ejecuta una vez al inicio de la prueba
export function setup() {
  // Verificar que el servicio esté disponible antes de iniciar las pruebas
  const healthCheck = http.get(`${BASE_URL}/api/facturas/1`);
  
  if (healthCheck.status !== 200) {
    throw new Error('El servicio de facturas no está disponible. Abortando pruebas.');
  }
  
  return { startTime: new Date().toISOString() };
}

// Función de cierre que se ejecuta una vez al finalizar la prueba
export function teardown(data) {
  console.log(`Prueba iniciada en: ${data.startTime}`);
  console.log(`Prueba finalizada en: ${new Date().toISOString()}`);
}