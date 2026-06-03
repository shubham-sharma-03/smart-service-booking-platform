const form = document.getElementById("bookingForm");
const table = document.getElementById("bookingTable");
const message = document.getElementById("message");

form.addEventListener("submit", e => {
    e.preventDefault();

    const booking = {
        userName: document.getElementById("username").value,
        serviceType: document.getElementById("serviceType").value,
        providerName: document.getElementById("providerName").value
    };

    fetch("/api/bookings", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(booking)
    })
        .then(res => res.json())
        .then(() => {
            message.style.color = "green";
            message.innerText = "✅ Booking Successful!";
            form.reset();
            loadBookings();
        })
        .catch(() => {
            message.style.color = "red";
            message.innerText = "❌ Failed to book";
        });
});

function loadBookings() {
    fetch("/api/bookings")
        .then(res => res.json())
        .then(data => {
            table.innerHTML = "";
            data.forEach(b => {
                table.innerHTML += `
                    <tr>
                        <td>${b.id}</td>
                        <td>${b.userName}</td>
                        <td>${b.serviceType}</td>
                        <td>${b.providerName}</td>
                        <td><span class="status ${b.status}">${b.status}</span></td>
                        <td>${b.tokenNumber}</td>
                        <td>
                            <a href="/technician/${b.id}" class="track-btn">📍 Track</a>
                        </td>
                    </tr>
                `;
            });
        });
}

document.addEventListener("DOMContentLoaded", loadBookings);
function confirmBooking(id) {
  fetch(`/api/bookings/${id}?status=CONFIRMED`, {
    method: "PUT"
  }).then(() => {
    alert("✅ Technician assigned. SMS sent!");
    loadBookings();
  });
}
