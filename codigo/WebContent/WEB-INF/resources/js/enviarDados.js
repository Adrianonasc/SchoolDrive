function cadDiciplina(){
    var doc = document;
    var codigo = doc.getElementById("tCodigo");
    var nome = doc.getElementById("tNomeDiciplina");
    var descri = doc.getElementById("tdescri");
    var er = /[0-9]/;
    alert(er.test(codigo));   
}