package com.qqServer;

import com.pack.Message;

public class Group extends Message implements Comparable{
	   /**
	    * ±àºÅ
	   */
	   private String no = "";
		/**
		 * ÐÕÃû
		 */
		private String name = "";
		/**
		 * ÈÕÆÚ
		 */
		private String groupdate = "";
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getGroupdate() {
			return groupdate;
		}
		public void setGroupdate(String groupdate) {
			this.groupdate = groupdate;
		}
		public String getNo() {
			return no;
		}
		public void setNo(String no) {
			this.no = no;
		}
		@Override
		public int compareTo(Object arg0) {
			return this.no.compareTo(arg0.toString());
		}
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return name;
		}
}