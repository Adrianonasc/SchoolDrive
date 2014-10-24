/* js Responsavel Pela Validaçoes*/

function validarData(dataNasc) {
    try {
        var data = new String();
        data = dataNasc.value;

        if (data == "") {
            alert("Data Invalida");
            return false;
        }
        var dataFormatada;
        dataFormatada = " ";
        var i = new Number();
        dataFormatada = data.slice(8, 10);
        dataFormatada = dataFormatada.concat("-");
        dataFormatada = dataFormatada.concat(data.slice(5, 7));
        dataFormatada = dataFormatada.concat("-");
        dataFormatada = dataFormatada.concat(data.slice(0, 4));
        //alert(dataFormatada);	
        return dataFormatada;

    } catch (err) {

    }
}
function isNun(idvalor) {
    try {
        var numero = parseInt(idvalor.value);
        return true;
    } catch (erro) {
        return false;
    }
}

function valida(myform) {
    var d = document.getElementById(myform);
    var erro = "Campo Obrigatorio";
    var houveCampo = false;
    for (var i = 0; i < (d.length - 2); i++) {
        if (d[i].value == "" && d[i].alt == "required") {
            bordaErro(d[i].id);
            houveCampo = true;
        }
    }
    if (houveCampo) {
        alert("Há Campo Inválido");
    }
}

function bordaErro(idCompomente) {
    var comp = document.getElementById(idCompomente);
    comp.className = "erroCampo";
}
