package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.service.EmpService;
import com.example.demo.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

// @Controller : URL을 호출했을때 응답을 처리
@Controller
public class CommonController {
	
	@Autowired
	private EmpService empService;
	
	@GetMapping("/test")
	public String test() {
		List<EmpVO> list = empService.selectEmp();
		return "blank";
	}

	/**
	 * @RequestMapping 요청한 URL이 어떤 
	 * 메서드와 연결되는 것인지 처리
	 * @RequestMapping("/", "/index")
	 * @RequestMapping(value = "/")
	 * @RequestMapping(value = "/", method=RequestMethod.GET)
	 * @return
	 */
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		System.out.println("index 페이지");
		model.addAttribute("message", "hello");
		
		HttpSession session = request.getSession();
		EmpVO empVO = (EmpVO) session.getAttribute("userInfo");
		
		if (empVO != null) {
			model.addAttribute("userInfo", empVO);
		} else {
			model.addAttribute("userInfo", new EmpVO());
		}
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("name", "최종선");
		map.put("age", 30);
		map.put("tel", "010-1111-2222");
		model.addAttribute("obj", map);
		
		List<HashMap<String, Object>> list = new ArrayList<>();
		HashMap<String, Object> item = new HashMap<>();
		item.put("name", "사과");
		item.put("price", 1000);
		list.add(item);
		
		item = new HashMap<>();
		item.put("name", "딸기");
		item.put("price", 2000);
		list.add(item);
		
		item = new HashMap<>();
		item.put("name", "포도");
		item.put("price", 1500);
		list.add(item);
		
		item = new HashMap<>();
		item.put("name", "바나나");
		item.put("price", 4000);
		list.add(item);
		
		System.out.println(list.toString());
		
		model.addAttribute("list", list);
		
		return "index";
	}
	
	
}
