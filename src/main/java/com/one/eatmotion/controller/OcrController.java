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
import com.one.eatmotion.service.ShopService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OcrController {

	private final ShopService shopService;

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
					//System.out.println(address);
					List<Shop> checkaddress = shopService.findByAddress(address);
					for(Shop s:checkaddress) {
						if(address.equals(s.getAddress())) {
							String name = s.getName();
							return name;
						}
					}
					return "해당 음식점은 등록되지 않았습니다.";

      } catch (IllegalStateException | IOException e) {
					return "error";
				}
    }
		return "error";
	}

	@PostMapping("ocrSave")
	@ResponseBody
	public List<Shop> OcrSave(MultipartFile uploadFile) {
		if(uploadFile !=null) {
			File f=new File("C:\\Temp2\\"+uploadFile.getOriginalFilename());
			try {
				uploadFile.transferTo(f);
				String address = ocrService.ocrSave(f);
				List<Shop> checkaddress = shopService.findByAddress(address);
				System.out.println(checkaddress);
				return checkaddress;
      } catch (IllegalStateException | IOException e) {
				return null;
			}

		}
		return null;
	}
}
