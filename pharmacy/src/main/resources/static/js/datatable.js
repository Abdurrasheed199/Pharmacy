$(document).ready( function () {
    var table = $('#testingTable').DataTable({
        "sAjaxSource": "/testing",
        "sAjaxDataProp": "",
        "order": [[ 0, "asc" ]],
        "aoColumns": [
            { "mData": "S/N"},
            { "mData": "TripNumber" },
            { "mData": "Bus" },
            { "mData": "takeOffTime" },
            { "mData": "landingTime" },
            { "mData": "takeOffPoint"},
            { "mData": "destinationPoint" },
            { "mData": "availableSeats" }

        ]
    })
});