var loading = document.getElementById('loading');
var mensaje = document.getElementById('mensaje');

var boton = document.getElementById('json_post');
boton.addEventListener('click', function () {
    loading.style.display = 'block';
    axios.post('http://digital-queue-404.herokuapp.com/queues', {

        name: $('#recipient-name').val(),
        identifier: $('#recipient-letter').val(),
        status: true

    })
        .then(function (res) {
            if (res.status == 201) {
                mensaje.innerHTML = 'El nuevo Post ha sido almacenado con id: ' + res.data.id;
            }
            window.location.reload
        })
        .catch(function (err) {
            console.log(err);
        })
        .then(function () {
            loading.style.display = 'none';
        });
});


axios.get('http://digital-queue-404.herokuapp.com/queues')
    .then(response => {

        mydata = response.data;

        mydata = mydata._embedded.queueList;
        // $('#table1').bootstrapTable({
        //     data: mydata
        // });
        console.log(mydata);
        mydata.forEach(service => {
            $('#servicesTable').append(`
                <tr>
                    <td>` + service.name + `</td>
                    <td>` + service.name[0] + `</td>
                    <td>
                        <input type="checkbox" checked data-toggle="toggle" data-onstyle="outline-success" data-offstyle="outline-danger">
                    </td>
                    <td>
                        <button type="button" onclick="window.location.href='/attentionPoints.html?queueName=` + service.name + ` '" class="btn btn-info">
                            <i class="far fa-trash-alt"></i>
                        </button>
                    </td>
                    <td>
                        <button type="button" onclick="deleteQueue(`+ service.id +`)" class="btn btn-danger">
                            <i class="far fa-trash-alt"></i>
                        </button>
                    </td>
                </tr>
            `)
        });
    })
    .catch(e => {
        // Capturamos los errores
    })



function deleteQueue(id) {
    axios.delete("http://digital-queue-404.herokuapp.com/queues/"+ id ).then(function (response) {
        window.location.reload
    })

}


