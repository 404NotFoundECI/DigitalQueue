
var loading = document.getElementById('loading');
var mensaje = document.getElementById('mensaje');
var boton = document.getElementById('json_post');
boton.addEventListener('click', () => {
    loading.style.display = 'block';
    //https://digital-queue-404.herokuapp.com/users
    axios.post('https://digital-queue-404.herokuapp.com/users', {
        name: $('#name').val(),
        email: $('#email').val(),
        password: $('#password').val(),
        role: $('#roleSelect').val()
    })
        .then(function (res) {
            if (res.status == 201) {
                mensaje.innerHTML = 'El nuevo Post ha sido almacenado con id: ' + res.data.id;
            }
        })
        .catch(function (err) {
            console.log(err);
        })
        .then(function () {
            loading.style.display = 'none';
        });
});

//http://digital-queue-404.herokuapp.com/users
axios.get('https://digital-queue-404.herokuapp.com/users')
    .then(response => {
        mydata = response.data;
        mydata = mydata._embedded.userList;
        // $('#table1').bootstrapTable({
        //     data: mydata
        // });
        console.log(mydata);
        mydata.forEach(employee => {
            $('#employeeTable').append(`
                <tr>
                    <td>` + employee.name + `</td>
                    <td>` + employee.email + `</td>
                    <td>` + employee.role + `</td>
                    <td>
                        <button type="button" class="btn btn-danger">
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