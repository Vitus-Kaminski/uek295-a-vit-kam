const API_BASE = 'http://localhost:8080/api/v1/orders';

// Load all orders
async function getAllOrders() {
    const res = await fetch(`${API_BASE}`);
    const orders = await res.json();
    renderTable('ordersTable', orders, ['id', 'status', 'comment', 'date']);
}

// Load all trackings
async function getAllTrackings() {
    const res = await fetch(`${API_BASE}/trackings`);
    const trackings = await res.json();
    renderTable('trackingsTable', trackings, ['trackingId', 'code', 'status', 'lastUpdate']);
}

// Create order
const createForm = document.getElementById('createOrderForm');
createForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData(createForm);

    const order = {
        status: formData.get('status'),
        comment: formData.get('comment'),
        date: formData.get('date'),
        tracking: {
            code: formData.get('trackingCode'),
            status: formData.get('trackingStatus'),
            lastUpdate: formData.get('trackingUpdate')
        }
    };

    const res = await fetch(`${API_BASE}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(order)
    });

    const result = await res.json();
    document.getElementById('output').textContent = JSON.stringify(result, null, 2);
    getAllOrders();
});

// Render table from data
function renderTable(containerId, data, fields) {
    const container = document.getElementById(containerId);
    if (!data.length) {
        container.innerHTML = '<p>Keine Daten verfügbar.</p>';
        return;
    }

    let table = '<table><thead><tr>';
    fields.forEach(f => table += `<th>${f}</th>`);
    table += '</tr></thead><tbody>';

    data.forEach(row => {
        table += '<tr>';
        fields.forEach(f => {
            table += `<td>${row[f] !== undefined ? row[f] : ''}</td>`;
        });
        table += '</tr>';
    });

    table += '</tbody></table>';
    container.innerHTML = table;
}

// Filters
async function filterOrdersByComment() {
    const val = document.getElementById('filterComment').value;
    const res = await fetch(`${API_BASE}/comment/${val}`);
    const data = await res.json();
    renderTable('ordersTable', data, ['id', 'status', 'comment', 'date']);
}

async function filterOrdersByStatus() {
    const val = document.getElementById('filterStatus').value;
    const res = await fetch(`${API_BASE}/status?status=${val}`);
    const data = await res.json();
    renderTable('ordersTable', data, ['id', 'status', 'comment', 'date']);
}

async function filterOrdersByDate() {
    const after = document.getElementById('orderAfter').value;
    const before = document.getElementById('orderBefore').value;
    let query = [];
    if (after) query.push(`after=${after}`);
    if (before) query.push(`before=${before}`);

    const res = await fetch(`${API_BASE}/date?${query.join('&')}`);
    const data = await res.json();
    renderTable('ordersTable', data, ['id', 'status', 'comment', 'date']);
}

async function filterTrackingsByDate() {
    const after = document.getElementById('trackAfter').value;
    const before = document.getElementById('trackBefore').value;
    let query = [];
    if (after) query.push(`after=${after}`);
    if (before) query.push(`before=${before}`);

    const res = await fetch(`${API_BASE}/trackings/date?${query.join('&')}`);
    const data = await res.json();
    renderTable('trackingsTable', data, ['trackingId', 'code', 'status', 'lastUpdate']);
}

async function filterTrackingsByCode() {
    const val = document.getElementById('filterTrackingCode').value;
    const res = await fetch(`${API_BASE}/trackings/code/${val}`);
    const data = await res.json();
    renderTable('trackingsTable', data, ['trackingId', 'code', 'status', 'lastUpdate']);
}
