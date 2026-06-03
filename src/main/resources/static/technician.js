const bookingId = window.location.pathname.split("/").pop();
document.getElementById("bookingId").innerText = bookingId;

let lat = 28.6139;
let lng = 77.2090;

function updateMap() {
    lat += (Math.random() - 0.5) * 0.001;
    lng += (Math.random() - 0.5) * 0.001;

    const mapUrl = `https://www.google.com/maps?q=${lat},${lng}&output=embed`;
    document.getElementById("map").src = mapUrl;
}

// update every 5 seconds
setInterval(updateMap, 5000);
updateMap();
