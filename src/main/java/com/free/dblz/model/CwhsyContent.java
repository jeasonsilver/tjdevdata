package com.free.dblz.model;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("t_dblz_cwhsy_content") 
public class CwhsyContent {
	
	@Id
	private Long id = null;// id
	@Column
	private Long fid = null;// pid
	@Column
	private String nr = null;// 内容
	
	@One(target = Cwhsy.class, field = "fid")
	private Cwhsy rddtxx;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public Cwhsy getRddtxx() {
		return rddtxx;
	}

	public void setRddtxx(Cwhsy rddtxx) {
		this.rddtxx = rddtxx;
	}
	
	
	
	
}
