axios.get('https://digital-queue-404.herokuapp.com/services/')
    .then(response => {
        var services = response.data._embedded.serviceList;
        services.forEach(service => {
            $('#services').append(`<option>` + service.name + `</option>`);
        });
        localStorage.setItem('services', JSON.stringify(services));
    })

function saveTurn(turn) {
    axios.post("https://digital-queue-404.herokuapp.com/turns", turn)
        .then(response => {
            alert('Turn successfully created.')
        });
}

function request() {
    var service = JSON.parse(localStorage.getItem('services')).find(service => {
        return service.name == $('#services').val();
    })
    axios.get('https://digital-queue-404.herokuapp.com/turns/count?service=' + service.name)
        .then(response => {
            var code = service.identifier + (response.data + 1);
            console.log('Code: ' + code);
            var turn = {
                code: code,
                clientName: $('#name').val(),
                requestedDateTime: new Date(),
                attendedDateTime: null,
                service: service,
                attended: false,
                cancelled: false,
                attentionPoint: null
            }
            saveTurn(turn);
        });
}