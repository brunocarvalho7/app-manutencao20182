$(function () {
 	M.AutoInit();

    if($("#mensagem").text()){
    	M.toast({html: $("#mensagem").text()});
    }

    if($("#comando").text() == "atualizar"){
        $("#formDados").attr("action", $("#formDados").attr("data-id")+"atualizar");
        M.Modal.getInstance(modalDados).open();
    }

    $("")
});