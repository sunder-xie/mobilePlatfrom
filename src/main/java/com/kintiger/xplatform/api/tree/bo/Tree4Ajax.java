package com.kintiger.xplatform.api.tree.bo;

import java.io.Serializable;

/**
 * Tree4Ext.
 * 
 * @author xujiakun
 * 
 */
public class Tree4Ajax implements Serializable {

	private static final long serialVersionUID = -8283009769200561139L;

	private String id;

	private String text;

	private String cls;

	private String href;

	private String hrefTarget;

	private Boolean leaf;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

}
