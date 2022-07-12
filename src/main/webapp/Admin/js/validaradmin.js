function validarad() {

    var actn, lnames ,eemails, pwr, telef, expresión1, expresión2;
    var actn = document.getElementById('actn').value;
    var lnames = document.getElementById("lnames").value;
    var eemails = document.getElementById("eemails").value;
    var pwr = document.getElementById("pwr").value;
    var telef = document.getElementById("telef").value;
    expresión1 = /\w+@\w+\.[a-zA-Z]/;
    expresión2 = /\d{7,10}$/;
    
    
    if(actn==0){
        Swal.fire("Ingrese el nombre");
        return false;
    }
    if (actn.length>15){
        Swal.fire("El nombre es muy largo")
        return false;
    }
    if(lnames==0){
        Swal.fire("Ingrese el un apellido");
        return false;
    }
    if (lnames.length>20){
        Swal.fire("El apellido es muy largo");
        return false;
    }
    if(eemails==0){
        Swal.fire("Ingrese un correo");
        return false;
    }
    if(!expresión1.test(eemails)){
        Swal.fire("El correo no es valido");
        return false;
    }
    if(pwr==0){
        Swal.fire("Ingrese la contraseña actual");
        return false;
    }
    if(telef==0){
        Swal.fire("Ingrese un número de teléfono");
        return false;
    }
    if(!expresión2.test(telef)){
        Swal.fire("El número de teléfono no es valido");
        return false;
    }   
    if (actn && lnames &&  eemails && pwr && telef && expresión1, expresión2) {
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })
        
        Toast.fire({
            icon: 'success',
            title: 'Datos Actualizados Correctamente'
        })
    return false;
    }

}
function validarproca(){

    
var ids, na, ca, fe, pr;
var ids = document.getElementById('ids').value;
var na = document.getElementById('na').value;
var ca = document.getElementById('ca').value;
var fe = document.getElementById('fe').value;
var pr = document.getElementById('pr').value;

if(ids==0) {
    Swal.fire("Ingrese el id del producto");
    return false;
}
if(na==0) {
    Swal.fire("Ingrese el nombre del producto  ");
    return false;
}
if(ca==0) {
    Swal.fire("Ingrese la cantidad del producto  ");
    return false;
}
if(fe==0) {
    Swal.fire("Ingrese la fecha del producto  ");
    return false;
}
if(pr==0) {
    Swal.fire("Ingrese  el precio del producto ");
    return false;
}

if (ids && na && ca && fe && pr ) {
const Toast = Swal.fire({
    title: 'Quiere guardar los cambios?',
    showDenyButton: true,
    showCancelButton: false,
    confirmButtonText: 'Guardar',
    denyButtonText: `No guardar`,
    }).then((result) => {
    /* Read more about isConfirmed, isDenied below */
    if (result.isConfirmed) {
        Swal.fire('Guardado!', '', 'success')
    } else if (result.isDenied) {
        Swal.fire('No se guardaron! ', '', 'canceled')
    }})
    return false
}}
function procavalidar(){

var idsf, naf, caf, fef, prf;
var idsf = document.getElementById('idsf').value;
var naf = document.getElementById('naf').value;
var caf = document.getElementById('caf').value;
var fef = document.getElementById('fef').value;
var prf = document.getElementById('prf').value;

if(idsf==0) {
    Swal.fire("Ingrese el id del producto");
    return false;
}
if(naf==0) {
    Swal.fire("Ingrese el nombre del producto  ");
    return false;
}
if(caf==0) {
    Swal.fire("Ingrese la cantidad del producto  ");
    return false;
}
if(fef==0) {
    Swal.fire("Ingrese la fecha del producto  ");
    return false;
}
if(prf==0) {
    Swal.fire("Ingrese  el precio del producto ");
    return false;
}

if (idsf && naf && caf && fef && prf ) {
const Toast = Swal.fire({
    title: 'Quiere guardar los cambios?',
    showDenyButton: true,
    showCancelButton: false,
    confirmButtonText: 'Guardar',
    denyButtonText: `No guardar`,
    }).then((result) => {
    /* Read more about isConfirmed, isDenied below */
    if (result.isConfirmed) {
        Swal.fire('Guardado!', '', 'success')
    } else if (result.isDenied) {
        Swal.fire('No se guardaron! ', '', 'canceled')
    }})
    return false
}}

function validarca(){

var sid, naa;
var sid= document.getElementById('sid').value;
var naa = document.getElementById('naa').value;


if(sid==0) {
    Swal.fire("Ingrese el id de la categoria");
    return false;
}
if(naa==0) {
    Swal.fire("Ingrese el nombre de la categoria");
    return false;
}
if (sid && naa) {
const Toast = Swal.fire({
    title: 'Quiere guardar los cambios?',
    showDenyButton: true,
    showCancelButton: false,
    confirmButtonText: 'Guardar',
    denyButtonText: `No guardar`,
    }).then((result) => {
    /* Read more about isConfirmed, isDenied below */
    if (result.isConfirmed) {
        Swal.fire('Guardado!', '', 'success')
    } else if (result.isDenied) {
        Swal.fire('No se guardaron! ', '', 'canceled')
    }})
    return false
}}
function cavalidar(){

var sidf, naaf;
var sidf= document.getElementById('sidf').value;
var naaf = document.getElementById('naaf').value;


if(sidf==0) {
    Swal.fire("Ingrese el id de la categoria");
    return false;
}
if(naaf==0) {
    Swal.fire("Ingrese el nombre de la categoria");
    return false;
}
if (sidf && naaf) {
const Toast = Swal.fire({
    title: 'Quiere guardar los cambios?',
    showDenyButton: true,
    showCancelButton: false,
    confirmButtonText: 'Guardar',
    denyButtonText: `No guardar`,
    }).then((result) => {
    /* Read more about isConfirmed, isDenied below */
    if (result.isConfirmed) {
        Swal.fire('Guardado!', '', 'success')
    } else if (result.isDenied) {
        Swal.fire('No se guardaron! ', '', 'canceled')
    }})
    return false
}}

function validar_inquietudes() {
    var respuesta_inquietud= document.getElementById("respuesta_inquietud").value;

    if (respuesta_inquietud==0)
    {
        Swal.fire("Responda la inquietud para enviar la respuesta");
    return false;

    }

    if (respuesta_inquietud) {
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })
        
        Toast.fire({
            icon: 'success',
            title: 'PQRS Respondida'
        })
    return false;
    }
}

function sugerencias() {
    var agradecimientos="muchas gracias por tu sugerencia";

    if (agradecimientos) {
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })
        
        Toast.fire({
            icon: 'success',
            title: 'Enviando confirmación de mensaje leído'
        })
    return false;
        
    }
    
}
function validar_problematicas() {
    var respuesta_problematica= document.getElementById("respuesta_problematica").value;

    if (respuesta_problematica==0)
    {
        Swal.fire("Responda la problemática para enviar la respuesta");
    return false;

    }

    if (respuesta_problematica) {
        const Toast = Swal.mixin({
            toast: true,
            position: 'top-end',
            showConfirmButton: false,
            timer: 3000,
            timerProgressBar: true,
            didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
            }
        })
        
        Toast.fire({
            icon: 'success',
            title: 'PQRS Respondida'
        })
    return false;
    }
}