package cn.zhx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.zhx.common.utils.JsonUtils;
import cn.zhx.pojo.Ad1Node;
import cn.zhx.service.ContentSerivce;

@Controller
public class IndexController {
	
	@Value("${AD1_CATEGORY_ID}")
	private Long AD1_CATEGORY_ID;
	@Value("${AD1_WIDTH}")
	private Integer AD1_WIDTH;
	@Value("${AD1_WIDTH_B}")
	private Integer AD1_WIDTH_B;
	@Value("${AD1_HEIGHT}")
	private Integer AD1_HEIGHT;
	@Value("${AD1_HEIGHT_B}")
	private Integer AD1_HEIGHT_B;
	
	@Autowired
	private ContentSerivce contentSerivce;

	@RequestMapping("index")
	public String index(Model model){
		List<Ad1Node> nodeList = contentSerivce.getContentListForPortal(AD1_CATEGORY_ID);
		for (Ad1Node ad1Node : nodeList) {
			ad1Node.setHeight(AD1_HEIGHT);
			ad1Node.setHeightB(AD1_HEIGHT_B);
			ad1Node.setWidth(AD1_WIDTH);
			ad1Node.setWidthB(AD1_WIDTH_B);
		}
		String objectToJson = JsonUtils.objectToJson(nodeList);
		model.addAttribute("ad1", objectToJson);
		return "index";
	}
}
