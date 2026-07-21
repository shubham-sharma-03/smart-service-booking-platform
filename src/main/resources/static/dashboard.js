function escapeHtml(text) {
  if (!text) return '';
  const div = document.createElement('div');
  div.textContent = text;
  return div.innerHTML;
}

// ===================== DASHBOARD =====================
let allBookings = [];

async function loadDashboard() {
  if (!Auth.isLoggedIn()) return;

  document.getElementById('dashboard').classList.remove('hidden');

  try {
    const data = await apiRequest('/dashboard');

    allBookings = Array.isArray(data.bookings) ? data.bookings : [];
    const tickets = Array.isArray(data.tickets) ? data.tickets : [];

    document.getElementById('kpiTotalBookings').textContent = data.totalBookings || 0;
    document.getElementById('kpiPending').textContent = data.pending || 0;
    document.getElementById('kpiCompleted').textContent = data.completed || 0;
    document.getElementById('kpiTickets').textContent = data.openTickets || 0;

    renderBookingsTable(allBookings);
    if (typeof renderCharts === 'function') renderCharts(allBookings);
  } catch (err) {
    console.log('Dashboard error:', err.message);
  }
}

function renderKpis(bookings, tickets) {
  const total = bookings.length;
  const pending = bookings.filter(b => b.status === 'PENDING').length;
  const completed = bookings.filter(b => b.status === 'COMPLETED').length;
  const openTickets = Array.isArray(tickets) ? tickets.filter(t => t.status !== 'CLOSED').length : 0;

  document.getElementById('kpiTotalBookings').textContent = total;
  document.getElementById('kpiPending').textContent = pending;
  document.getElementById('kpiCompleted').textContent = completed;
  document.getElementById('kpiTickets').textContent = openTickets;
}

function animateCount(el, target) {
  let current = 0;
  const step = Math.max(1, Math.ceil(target / 30));
  const timer = setInterval(() => {
    current += step;
    if (current >= target) { current = target; clearInterval(timer); }
    el.textContent = current;
  }, 30);
}

function renderBookingsTable(bookings) {
  const tbody = document.getElementById('bookingsTableBody');
  tbody.innerHTML = '';

  if (!bookings.length) {
    tbody.innerHTML = `<tr><td colspan="6" style="text-align:center; color:var(--text-muted); padding:24px;">No bookings yet</td></tr>`;
    return;
  }

  bookings.forEach(b => {
    const tr = document.createElement('tr');
    tr.className = 'row-fade-in';

    const dateStr = b.bookingDate ? new Date(b.bookingDate).toLocaleDateString() : '-';

    // FIXED: read the random-assignment snapshot field first,
    // fall back to the real relational technician if it was ever set
    const techName = b.assignedTechName
      ? b.assignedTechName
      : (b.technician ? b.technician.name : 'Unassigned');

    tr.innerHTML = `
      <td>#${b.id}</td>
      <td>${escapeHtml(b.serviceType || '-')}</td>
      <td>${escapeHtml(dateStr)}</td>
      <td><span class="status-badge status-${(b.status || '').toLowerCase()}">${escapeHtml((b.status || '').toLowerCase())}</span></td>
      <td>${escapeHtml(techName)}</td>
      <td>
        ${b.status === 'PENDING' || b.status === 'CONFIRMED'
          ? `<button class="row-action-btn" title="Cancel" onclick="cancelBooking(${b.id})"><i class="fa-solid fa-xmark"></i></button>`
          : ''}
      </td>
    `;
    tbody.appendChild(tr);
  });
}

document.addEventListener('DOMContentLoaded', () => {
  if (Auth.isLoggedIn()) loadDashboard();

  const searchInput = document.getElementById('bookingSearch');
  if (searchInput) {
    searchInput.addEventListener('input', () => {
      const term = searchInput.value.toLowerCase();
      const filtered = allBookings.filter(b =>
        (b.serviceType || '').toLowerCase().includes(term) ||
        (b.status || '').toLowerCase().includes(term) ||
        String(b.id).includes(term)
      );
      renderBookingsTable(filtered);
    });
  }

  const dashboardLink = document.getElementById('dashboardLink');
  if (dashboardLink) {
    dashboardLink.addEventListener('click', (e) => {
      if (!Auth.isLoggedIn()) {
        e.preventDefault();
        openModal('loginModal');
      }
    });
  }
});