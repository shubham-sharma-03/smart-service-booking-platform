// ===================== MAP: pick service location =====================
let bookingMap, bookingMarker;

function initBookingMap() {
  const mapEl = document.getElementById('bookingMap');
  if (!mapEl || typeof L === 'undefined') return;

  // Default center: Mumbai
  const defaultLatLng = [19.0760, 72.8777];

  bookingMap = L.map('bookingMap').setView(defaultLatLng, 12);

  L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; OpenStreetMap contributors',
    maxZoom: 19
  }).addTo(bookingMap);

  bookingMap.on('click', (e) => {
    setBookingMarker(e.latlng.lat, e.latlng.lng);
  });

  // Try to use browser geolocation if available
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
      (pos) => {
        const { latitude, longitude } = pos.coords;
        bookingMap.setView([latitude, longitude], 14);
        setBookingMarker(latitude, longitude);
      },
      () => { /* silently ignore denial */ },
      { timeout: 5000 }
    );
  }
}

function setBookingMarker(lat, lng) {
  if (bookingMarker) {
    bookingMarker.setLatLng([lat, lng]);
  } else {
    bookingMarker = L.marker([lat, lng]).addTo(bookingMap);
  }
  document.getElementById('latitude').value = lat.toFixed(6);
  document.getElementById('longitude').value = lng.toFixed(6);
}

document.addEventListener('DOMContentLoaded', () => {
  // Defer slightly since Leaflet script is loaded with `defer`
  window.addEventListener('load', initBookingMap);
});