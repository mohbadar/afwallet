/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package af.asr.notification;

public interface ServiceConstants {
	String LOGGER_NAME = "notification-logger";
	
	String MAIL_TRANSPORT_PROTOCOL_PROPERTY = "mail.transport.protocol";
	String MAIL_SMTP_AUTH_PROPERTY = "mail.smtp.auth";
	String MAIL_SMTP_STARTTLS_ENABLE_PROPERTY = "mail.smtp.starttls.enable";
	String MAIL_SMTP_TIMEOUT_PROPERTY = "";
	
	String GOOGLE_MAIL_SERVER = "smtp.gmail.com";
	String YAHOO_MAIL_SERVER = "smtp.mail.yahoo.com";
}