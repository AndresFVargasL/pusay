function abrirReporte(basepath, extension, reportName) {
	var fech= new Date().getTime();
	window.open(basepath + '/exportDocument?format='+extension+'&reportName='+reportName+'&useDataSource=true'+'&nocache='+fech);
	return false;
}