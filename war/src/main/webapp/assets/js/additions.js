createTableWithDataFromDB({get: 'additions-getData'}, 'additions-table', {
    columns: [
        { "sTitle": "Id" },
        { "sTitle": "Name" },
        // { "sTitle": "Price" },
        { "sTitle": "Description" },
        { "sTitle": "Published" }

    ]
});