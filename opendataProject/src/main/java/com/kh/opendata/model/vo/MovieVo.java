package com.kh.opendata.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieVo {
	
	private String rank;
	private String movieNm;
	private String openDt;
	private String salesInten;
}
