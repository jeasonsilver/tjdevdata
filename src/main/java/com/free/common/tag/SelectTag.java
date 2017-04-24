package com.free.common.tag;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import com.free.system.service.CodeDicService;
import com.free.common.utils.spring.SpringContextHolder;
/**
 * 分页标签.
 * 
 */
public class SelectTag extends TagSupport {

	private static final long serialVersionUID = -5784043607741818040L;


	private String name;
	private String id;
	private String defaultName;
	private boolean showTorF;//显示是否2个选项
	private String section;//
	private String sectionname;//
	private String value;//
	private String cssClass;//
	private String label;//
	private String items;//以字符串显示所有option  key@value#
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.jsp.tagext.TagSupport#doEndTag()
	 */
	@Override
	public int doEndTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try {
			//out.println(buildStyles());
			out.println(buildPage());
			out.println(buildScript());
		} catch (IOException e) {
			throw new JspException(e);
		}

		return super.doEndTag();
	}

	private String buildPage() {
		if (StringUtils.isEmpty(value) && pageContext.getRequest().getAttribute(name)!=null){
			//System.out.println(pageContext.getRequest().getAttribute(name));
			value= pageContext.getRequest().getAttribute(name).toString();
		}
		//pages
		StringBuffer pageBuffer = new StringBuffer();
		if (StringUtils.isNotEmpty(label)){
			pageBuffer.append("<p>");
			pageBuffer.append("<label>"+label+"：</label>");
		}
			
		pageBuffer.append("<select");
		pageBuffer.append(" name=\"").append(name).append("\"");
		if (id ==null){
			pageBuffer.append(" id=\"").append(name).append("\"");
		}else{
			pageBuffer.append(" id=\"").append(id).append("\"");
		}
		if (cssClass !=null){
			pageBuffer.append(" class=\"").append(cssClass).append("\"");
		}
		pageBuffer.append(" >\n");

		if (defaultName !=null){//默认选项
			pageBuffer.append("<option value=\"\" >");
			pageBuffer.append(defaultName);
			pageBuffer.append("</option>");
		}
		if (section !=null){
			//Map<String, String> codemap = (Map<String, String>)pageContext.getSession().getServletContext().getAttribute("codeMap");
			
			CodeDicService codeDicService = (CodeDicService)SpringContextHolder.getBean("codeDicService"); //即可取到bean  
			Map<String, String> map = codeDicService.getRealCodeMapBySection(section);
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry key = (Entry) it.next();
				pageBuffer.append("<option value=\""+key.getKey()+"\" ");
				if (key.getKey().equals(value)){
					pageBuffer.append(" selected=\"selected\" ");
				}
				pageBuffer.append(">"+key.getValue()+"</option>");
			}
		}
		if (items !=null){
			String[] strs = items.split("#");
			for (String str : strs) {
				String[] in_str = str.split("@");
				pageBuffer.append("<option value=\""+in_str[0]+"\" ");
				if (StringUtils.isNotEmpty(in_str[0]) && in_str[0].equals(value)){
					pageBuffer.append(" selected=\"selected\" ");
				}
				pageBuffer.append(">"+in_str[1]+"</option>");
			}
		}
		if (sectionname !=null){
			//Map<String, String> codemap = (Map<String, String>)pageContext.getSession().getServletContext().getAttribute("codeMap");
			CodeDicService codeDicService = (CodeDicService)SpringContextHolder.getBean("codeDicService"); //即可取到bean  
			Map<String, String> map = codeDicService.getRealCodeMapBySectionName(sectionname);
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry key = (Entry) it.next();
				pageBuffer.append("<option value=\""+key.getKey()+"\" ");
				if (key.getKey().equals(value)){
					pageBuffer.append(" selected=\"selected\" ");
				}
				pageBuffer.append(">"+key.getValue()+"</option>");
			}
		}
		if (showTorF){
			pageBuffer.append("<option value=\"1\" >是</option>");
			pageBuffer.append("<option value=\"0\" >否</option>");
		}
		pageBuffer.append("</select>");
		if (StringUtils.isNotEmpty(label)){
			pageBuffer.append("</p>");
		}
		
		//标签有缓存，所以需要重置成默认值
		value =null;
		return pageBuffer.toString();
	}

	private String buildScript() {
		StringBuffer scriptBuffer = new StringBuffer();
		scriptBuffer.append("<script type=\"text/javascript\">").append("\n");
		scriptBuffer.append("</script>").append("\n");

		return scriptBuffer.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDefaultName() {
		return defaultName;
	}

	public void setDefaultName(String defaultName) {
		this.defaultName = defaultName;
	}

	public boolean isShowTorF() {
		return showTorF;
	}

	public void setShowTorF(boolean showTorF) {
		this.showTorF = showTorF;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getSectionname() {
		return sectionname;
	}

	public void setSectionname(String sectionname) {
		this.sectionname = sectionname;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	
	
}
