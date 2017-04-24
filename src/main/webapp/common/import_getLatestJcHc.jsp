<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
var latestJcHc;
$(function() {
	latestJcHc=JSON.parse('${elf:getLatestJcAndHc(param.search_EQ_wjsslm)}');
	Free.getCodeNames("${ctx}","届","会次");
	$("[name=search_EQ_jc]").val(latestJcHc.jc);
	$("[name=search_EQ_hc]").val(latestJcHc.hc);
});
</script>