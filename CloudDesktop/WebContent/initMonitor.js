/**
 * 
 */
function initMonitorExe() {
	try {
		var wsh = new ActiveXObject("wscript.shell");
		wsh
				.run("file:///C:/Users/JustYoung/Desktop/�½� Microsoft PowerPoint ��ʾ�ĸ�");
	} catch (e) {
		alert(e);
		return;
	}
}