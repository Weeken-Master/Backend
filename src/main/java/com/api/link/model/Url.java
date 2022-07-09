package com.api.link.model;

import java.util.Date;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Url {

	@PrimaryKey
	private String id;
	private String linkDefault;
	@JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private Date time;
	private int count;

	public Url(){}
	
	public Url(String id, String linkDefault){
		this.id = id; 
		this.linkDefault = linkDefault;
		this.time = new Date();
		this.count = 0;
	}

	public String getID(){
		return this.id;
	}
	public String getLinkDefault(){
		return this.linkDefault;
	}
	public Date getTime(){
		return this.time;
	}
	public void setTime(){
		this.time = new Date();
	}
	public int getCount(){
		return this.count;
	}
	public void inCount(){
		this.count += 1;
	}

}
