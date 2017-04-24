<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
var latestJcHc;
function getLatestJcHc(type) {
	Free.getCodeNames("${ctx}","届","会次","答复评价");
	$("[name=search_EQ_jc]").val(latestJcHc.jc);
	$("[name=search_EQ_hc]").val(latestJcHc.hc);
}
</script>