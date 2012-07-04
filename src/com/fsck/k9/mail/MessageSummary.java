package com.fsck.k9.mail;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import com.fsck.k9.mail.Message.RecipientType;

public class MessageSummary implements Parcelable {
	
	private List<String> from = new ArrayList<String>();
	private List<String> to = new ArrayList<String>();
	private List<String> cc = new ArrayList<String>();
	private String subject;
	
	private MessageSummary() {
	}
	
	public MessageSummary(Message message) throws MessagingException {
		for (Address a : message.getFrom()) {
			from.add(a.getAddress());
		}
		for (Address a : message.getRecipients(RecipientType.TO)) {
			to.add(a.getAddress());
		}
		for (Address a : message.getRecipients(RecipientType.CC)) {
			cc.add(a.getAddress());
		}

		subject = message.getSubject();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int arg1) {
		parcel.writeStringList(from);
		parcel.writeStringList(to);
		parcel.writeStringList(cc);
		parcel.writeString(subject);
	}
	
	public static Creator<MessageSummary> CREATOR = new Creator<MessageSummary>() {

		@Override
		public MessageSummary createFromParcel(Parcel parcel) {
			MessageSummary msg = new MessageSummary();
			parcel.readStringList(msg.from);
			parcel.readStringList(msg.to);
			parcel.readStringList(msg.cc);			
			msg.subject = parcel.readString();
			return msg;
		}

		@Override
		public MessageSummary[] newArray(int size) {
			return new MessageSummary[size];
		}
	};
}
