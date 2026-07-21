// ===================== CHARTS =====================
let statusChartInstance, trendChartInstance;

function renderCharts(bookings) {
  if (typeof Chart === 'undefined') return;
  renderStatusChart(bookings);
  renderTrendChart(bookings);
}

function renderStatusChart(bookings) {
  const ctx = document.getElementById('statusChart');
  if (!ctx) return;

  const statuses = ['PENDING', 'CONFIRMED', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED'];
  const counts = statuses.map(s => bookings.filter(b => b.status === s).length);
  const colors = ['#d97706', '#2563eb', '#7c3aed', '#16a34a', '#dc2626'];

  if (statusChartInstance) statusChartInstance.destroy();

  statusChartInstance = new Chart(ctx, {
    type: 'doughnut',
    data: {
      labels: statuses.map(s => s.replace('_', ' ')),
      datasets: [{ data: counts, backgroundColor: colors, borderWidth: 0 }]
    },
    options: {
      responsive: true,
      plugins: { legend: { position: 'bottom', labels: { boxWidth: 12, font: { size: 11 } } } }
    }
  });
}

function renderTrendChart(bookings) {
  const ctx = document.getElementById('trendChart');
  if (!ctx) return;

  // Group bookings by date
  const grouped = {};
  bookings.forEach(b => {
    const date = b.preferredDate || 'Unknown';
    grouped[date] = (grouped[date] || 0) + 1;
  });

  const labels = Object.keys(grouped).sort();
  const data = labels.map(l => grouped[l]);

  if (trendChartInstance) trendChartInstance.destroy();

  trendChartInstance = new Chart(ctx, {
    type: 'line',
    data: {
      labels,
      datasets: [{
        label: 'Bookings',
        data,
        borderColor: '#2563eb',
        backgroundColor: 'rgba(37,99,235,0.1)',
        fill: true,
        tension: 0.35,
        pointRadius: 3
      }]
    },
    options: {
      responsive: true,
      plugins: { legend: { display: false } },
      scales: { y: { beginAtZero: true, ticks: { precision: 0 } } }
    }
  });
}