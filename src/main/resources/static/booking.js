// ===================== TECHNICIAN POOL =====================
// Each technician tagged with the serviceType(s) they handle
const technicians = [
    { name: "Rahul Sharma", phone: "+91 9876543210", specialization: "Electrical Specialist", experience: "8 Years", completed: 214, eta: "18 Minutes", distance: "3.2 KM", rating: 4.9, services: ["ELECTRICAL"] },
    { name: "Aman Verma", phone: "+91 9988776655", specialization: "AC Technician", experience: "6 Years", completed: 186, eta: "15 Minutes", distance: "2.1 KM", rating: 4.7, services: ["AC_REPAIR"] },
    { name: "Rohan Gupta", phone: "+91 9123456789", specialization: "Plumbing Specialist", experience: "7 Years", completed: 243, eta: "12 Minutes", distance: "1.7 KM", rating: 4.8, services: ["PLUMBING"] },
    { name: "Vivek Patel", phone: "+91 9988123456", specialization: "Appliance Technician", experience: "9 Years", completed: 311, eta: "20 Minutes", distance: "4.5 KM", rating: 4.6, services: ["APPLIANCE"] },
    { name: "Neha Joshi", phone: "+91 9876123450", specialization: "Home Cleaning Specialist", experience: "5 Years", completed: 178, eta: "10 Minutes", distance: "1.2 KM", rating: 4.8, services: ["CLEANING"] }
];

function assignRandomTechnician(serviceType) {
    // Only pick from technicians who actually handle this service type
    let pool = technicians.filter(t => t.services.includes(serviceType));

    // Fallback: if no exact match found, use the full pool so the flow never breaks
    if (pool.length === 0) pool = technicians;

    const tech = pool[Math.floor(Math.random() * pool.length)];

    document.getElementById("techName").innerText = tech.name;
    document.getElementById("techPhone").innerText = tech.phone;
    document.getElementById("techSpecialization").innerText = tech.specialization;
    document.getElementById("techExperience").innerText = tech.experience;
    document.getElementById("completedJobs").innerText = tech.completed;
    document.getElementById("techEta").innerText = tech.eta;
    document.getElementById("techDistance").innerText = tech.distance;
    document.getElementById("techRating").innerText = tech.rating;
    document.getElementById("techStars").innerText = "⭐".repeat(Math.round(tech.rating));
    document.getElementById("techStatus").innerText = "🟢 On The Way";

    return tech;
}

document.addEventListener('DOMContentLoaded', () => {
    const bookingForm = document.getElementById('bookingForm');
    if (!bookingForm) return;

    // Make sure the card is hidden on page load, even if the HTML class got lost
    const techContainer = document.getElementById('technician-container');
    if (techContainer) techContainer.classList.add('hidden');

    bookingForm.addEventListener('submit', async function (e) {
        e.preventDefault();

        if (typeof Auth !== 'undefined' && !Auth.isLoggedIn()) {
            if (typeof openModal === 'function') openModal('loginModal');
            return;
        }

        const submitBtn = document.getElementById('bookingSubmitBtn');
        const btnText = submitBtn.querySelector('.btn-text');
        const spinner = submitBtn.querySelector('.spinner');
        btnText.classList.add('hidden');
        spinner.classList.remove('hidden');
        submitBtn.disabled = true;

        const serviceType = document.getElementById('serviceType').value;
        const assignedTech = assignRandomTechnician(serviceType);

        const bookingData = {
            serviceType: serviceType,
            bookingDate: document.getElementById('preferredDate').value,
            preferredTime: document.getElementById('preferredTime').value,
            customerPhone: document.getElementById('customerPhone').value,
            address: document.getElementById('address').value,
            latitude: document.getElementById('latitude').value,
            longitude: document.getElementById('longitude').value,
            notes: document.getElementById('notes').value,
            technicianName: assignedTech.name,
            technicianPhone: assignedTech.phone
        };

        try {
            const response = await apiRequest('/bookings', {
                method: 'POST',
                body: bookingData
            });

            document.getElementById('technician-container').classList.remove('hidden');
            bookingForm.reset();

            if (typeof showToast === 'function') {
                showToast(`Booking confirmed! ${assignedTech.name} is on the way.`, 'success');
            }

            if (typeof loadDashboard === 'function' && typeof Auth !== 'undefined' && Auth.isLoggedIn()) {
                loadDashboard();
            }
        } catch (err) {
            console.error(err);
            if (typeof showToast === 'function') {
                showToast('Booking failed. Please try again.', 'error');
            }
        } finally {
            btnText.classList.remove('hidden');
            spinner.classList.add('hidden');
            submitBtn.disabled = false;
        }
    });
});