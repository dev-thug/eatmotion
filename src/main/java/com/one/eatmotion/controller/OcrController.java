package com.one.eatmotion.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.one.eatmotion.entity.Shop;
import com.one.eatmotion.repository.ShopRepository;
import com.one.eatmotion.service.OcrService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OcrController {
	
	private final ShopRepository shopRepository;
	
	@Autowired
	OcrService ocrService;

	
	@PostMapping("ocrCheck")
	@ResponseBody
	public String OcrCheck(MultipartFile uploadFile) {
		if(uploadFile != null) {
			File f=new File("C:\\Temp2\\"+uploadFile.getOriginalFilename());
				try {
					uploadFile.transferTo(f);
					String address = ocrService.ocrCheck(f);
					System.out.println(address);
					List<Shop> checkaddress = shopRepository.findByAddress(address);
					for(Shop s:checkaddress) {
						if(address.equals(s.getAddress())) {
							return address;
						}
					}
					return "해당 음식점은 등록되지 않았습니다.";
					
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "error";
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "error";
				}
			}
		return "error";
	}
}
