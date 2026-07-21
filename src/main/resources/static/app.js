// ===================== GLOBAL CONFIG =====================
const API_BASE = '/api'; // adjust if backend is on a different host/port, e.g. 'http://localhost:8080/api'

const Auth = {
  getToken() { return localStorage.getItem('ssbp_token'); },
  setToken(token) { localStorage.setItem('ssbp_token', token); },
  clearToken() { localStorage.removeItem('ssbp_token'); },
  getUser() {
    const raw = localStorage.getItem('ssbp_user');
    return raw ? JSON.parse(raw) : null;
  },
  setUser(user) { localStorage.setItem('ssbp_user', JSON.stringify(user)); },
  clearUser() { localStorage.removeItem('ssbp_user'); },
  isLoggedIn() { return !!this.getToken(); },
  logout() { this.clearToken(); this.clearUser(); updateAuthUI(); showToast('Logged out successfully', 'success'); }
};

// ===================== API HELPER =====================
async function apiRequest(path, { method = 'GET', body = null, auth = true } = {}) {
  const headers = { 'Content-Type': 'application/json' };
  if (auth && Auth.getToken()) {
    headers['Authorization'] = `Bearer ${Auth.getToken()}`;
  }

  const res = await fetch(`${API_BASE}${path}`, {
    method,
    headers,
    body: body ? JSON.stringify(body) : null
  });

  let data = null;
  try { data = await res.json(); } catch (e) { /* no body */ }

  if (!res.ok) {
    const message = (data && (data.message || data.error)) || `Request failed (${res.status})`;
    throw new Error(message);
  }
  return data;
}

// ===================== NAVBAR / UI STATE =====================
function updateAuthUI() {
  const loggedIn = Auth.isLoggedIn();
  const user = Auth.getUser();

  document.getElementById('loginBtn').classList.toggle('hidden', loggedIn);
  document.getElementById('registerBtn').classList.toggle('hidden', loggedIn);
  document.getElementById('userMenu').classList.toggle('hidden', !loggedIn);
  document.getElementById('dashboardLink').classList.toggle('hidden', !loggedIn);

  if (loggedIn && user) {
    document.getElementById('userNameLabel').textContent = user.name || user.email || 'User';
  }
}

function openModal(id) { document.getElementById(id).classList.remove('hidden'); }
function closeModal(id) { document.getElementById(id).classList.add('hidden'); }

function setButtonLoading(btn, loading) {
  btn.disabled = loading;
  btn.querySelector('.btn-text').classList.toggle('hidden', loading);
  btn.querySelector('.spinner').classList.toggle('hidden', !loading);
}

// ===================== INIT =====================
document.addEventListener('DOMContentLoaded', () => {
  updateAuthUI();

  // Nav interactions
  document.getElementById('hamburger').addEventListener('click', () => {
    document.getElementById('navLinks').classList.toggle('open');
  });

  document.getElementById('loginBtn').addEventListener('click', () => openModal('loginModal'));
  document.getElementById('registerBtn').addEventListener('click', () => openModal('registerModal'));
  document.getElementById('logoutBtn').addEventListener('click', () => Auth.logout());
  document.getElementById('heroBookBtn').addEventListener('click', () => {
    document.getElementById('booking').scrollIntoView({ behavior: 'smooth' });
  });

 document.getElementById('heroTrackBtn').addEventListener('click', () => {
     if (!Auth.isLoggedIn()) {
         openModal('loginModal');
         return;
     }
     // Show dashboard section
     document.getElementById('dashboard').classList.remove('hidden');
     // Scroll to it
     document.getElementById('dashboard').scrollIntoView({ behavior: 'smooth' });
     // Load dashboard data
     if (typeof loadDashboard === 'function') loadDashboard();
 });

  document.querySelectorAll('.modal-close').forEach(btn => {
    btn.addEventListener('click', () => closeModal(btn.dataset.close));
  });
  document.querySelectorAll('.modal-overlay').forEach(overlay => {
    overlay.addEventListener('click', (e) => {
      if (e.target === overlay) overlay.classList.add('hidden');
    });
  });

  document.getElementById('switchToRegister').addEventListener('click', () => {
    closeModal('loginModal'); openModal('registerModal');
  });
  document.getElementById('switchToLogin').addEventListener('click', () => {
    closeModal('registerModal'); openModal('loginModal');
  });

  // ---------- LOGIN ----------
  document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const btn = document.getElementById('loginSubmitBtn');
    const errorEl = document.getElementById('loginError');
    errorEl.classList.add('hidden');
    setButtonLoading(btn, true);

    try {
      const payload = {
        email: document.getElementById('loginEmail').value.trim(),
        password: document.getElementById('loginPassword').value
      };
      const data = await apiRequest('/auth/login', { method: 'POST', body: payload, auth: false });
      Auth.setToken(data.token);
      Auth.setUser(data.user || { email: payload.email });
      updateAuthUI();
      closeModal('loginModal');
      showToast('Logged in successfully', 'success');
      if (typeof loadDashboard === 'function') loadDashboard();
    } catch (err) {
      errorEl.textContent = err.message;
      errorEl.classList.remove('hidden');
    } finally {
      setButtonLoading(btn, false);
    }
  });

  // ---------- REGISTER ----------
  document.getElementById('registerForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const btn = document.getElementById('registerSubmitBtn');
    const errorEl = document.getElementById('registerError');
    errorEl.classList.add('hidden');
    setButtonLoading(btn, true);

    try {
      const payload = {
        name: document.getElementById('regName').value.trim(),
        email: document.getElementById('regEmail').value.trim(),
        phone: document.getElementById('regPhone').value.trim(),
        password: document.getElementById('regPassword').value
      };
      await apiRequest('/auth/register', { method: 'POST', body: payload, auth: false });
      showToast('Account created — please log in', 'success');
      closeModal('registerModal');
      openModal('loginModal');
      document.getElementById('registerForm').reset();
    } catch (err) {
      errorEl.textContent = err.message;
      errorEl.classList.remove('hidden');
    } finally {
      setButtonLoading(btn, false);
    }
  });
});


// ---------- PASSWORD TOGGLE ----------
function setupPasswordToggle(inputId, buttonId) {
    const input = document.getElementById(inputId);
    const button = document.getElementById(buttonId);
    if (!input || !button) return;

    button.addEventListener('click', () => {
        const icon = button.querySelector('i');
        if (input.type === 'password') {
            input.type = 'text';
            icon.classList.remove('fa-eye');
            icon.classList.add('fa-eye-slash');
        } else {
            input.type = 'password';
            icon.classList.remove('fa-eye-slash');
            icon.classList.add('fa-eye');
        }
    });
}

setupPasswordToggle('loginPassword', 'toggleLoginPassword');
setupPasswordToggle('regPassword', 'toggleRegPassword');

async function loadStats() {
    try {
        const [bookingsRes, techsRes] = await Promise.all([
            apiRequest('/bookings/stats').catch(() => ({ completed: 0 })),
            apiRequest('/technicians/count').catch(() => 0)
        ]);

        const completedBookings = bookingsRes.completed || 0;
        const techCount = techsRes || 0;

        animateCount(document.getElementById('statBookings'), completedBookings);
        animateCount(document.getElementById('statTechs'), techCount);
        animateCount(document.getElementById('statCities'), 1); // Hardcoded or fetch from API
    } catch (err) {
        console.log('Stats not available');
    }
}

// Call on page load
document.addEventListener('DOMContentLoaded', () => {
    loadStats();
});


function escapeHtml(text) {
  if (!text) return '';
  const div = document.createElement('div');
  div.textContent = text;
  return div.innerHTML;
}