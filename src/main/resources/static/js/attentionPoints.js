
var loading = document.getElementById('loading');
var mensaje = document.getElementById('mensaje');
var boton = document.getElementById('json_post');
boton.addEventListener('click', function () {
    loading.style.display = 'block';
    //https://localhost:8080/users

    axios.post('https://localhost:8080/attentionPoints', {
        code: $('#recipient-code').val(),
        queue: getQueueById(2)

    })
        .then(function (res) {
            if (res.status == 201) {
                mensaje.innerHTML = 'El nuevo Post ha sido almacenado con id: ' + res.data.id;
            }
        })
        .catch(function (err) {
            console.log(err);
        })
});

//http://localhost:8080/users
axios.get('https://localhost:8080/attentionPoints')
    .then(response => {
        mydata = response.data;
        mydata = mydata._embedded.attentionPointList;
        // $('#table1').bootstrapTable({
        //     data: mydata
        // });
        console.log(mydata);
        mydata.forEach(attentionPoint => {
            $('#attentionPointsTable').append(`
                <tr>
                    <td>` + attentionPoint.code + `</td>
                    <td>` + "True" + `</td>
                    <td>` + "Sergio" + `</td>
                    <td>` + "1" + `</td>
                    <td>
                        <button type="button" onclick="deleteAttentionPoint(`+ attentionPoint.id + `)" class="btn btn-danger">
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

function deleteAttentionPoint(id) {
    axios.delete("https://localhost:8080/attentionPoints/" + id).then(function (response) {

    })

}

var queue = getParameterByName('queueId'); // "lorem"

function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, '\\$&');
    var regex = new RegExp('[?&]' + name + '(=([^&#]*)|&|#|$)'),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, ' '));
}


function getQueueById(queueid) {
    var queue
    axios.get('https://localhost:8080/queues/' + queueid)
        .then(response => {
        queue = response.data
        console.log(queue)

        }).catch(e => {
            // Capturamos los errores
        })

    return queue;
}