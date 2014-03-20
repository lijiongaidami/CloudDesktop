/**
 * 
 */
function initMonitorExe() {
	try {
		var wsh = new ActiveXObject("wscript.shell");
		wsh
				.run("file:///C:/Users/JustYoung/Desktop/新建 Microsoft PowerPoint 演示文稿");
	} catch (e) {
		alert(e);
		return;
	}
}