/*
 * Copyright (C) 2010 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gson;

import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;

public final class MixedStreamTest extends TestCase {

  private static final Car BLUE_MUSTANG = new Car("mustang", 0x0000FF);
  private static final Car BLACK_BMW = new Car("bmw", 0x000000);
  private static final Car RED_MIATA = new Car("miata", 0xFF0000);
  private static final String CARS_JSON = "[\n"
      + "  {\n"
      + "    \"name\": \"mustang\",\n"
      + "    \"color\": 255\n"
      + "  },\n"
      + "  {\n"
      + "    \"name\": \"bmw\",\n"
      + "    \"color\": 0\n"
      + "  },\n"
      + "  {\n"
      + "    \"name\": \"miata\",\n"
      + "    \"color\": 16711680\n"
      + "  }\n"
      + "]";


  public void testWriteMixedStreamed() throws IOException {
    Gson gson = new Gson();
    StringWriter stringWriter = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(stringWriter);

    jsonWriter.beginArray();//设置数组开始符：[
    jsonWriter.setIndent("  ");// 设置缩进为两个空格
    gson.toJson(BLUE_MUSTANG, Car.class, jsonWriter);// 第一个对象加入数组[\n      {\n      "name": "mustang",\n            "color": 255\n    }
    gson.toJson(BLACK_BMW, Car.class, jsonWriter);//第二个对象加入数组
    gson.toJson(RED_MIATA, Car.class, jsonWriter);//第三个对象加入数组
    jsonWriter.endArray();//数组结束符:」

    assertEquals(CARS_JSON,stringWriter.toString());
  }

  private static final String MyJSON2 = "{\"processGroups\":\"revision\"}";
  static final class MyClass2 {
    String processGroups;

    MyClass2(String processGroups) {
      this.processGroups = processGroups;
    }

    // used by Gson
    MyClass2() {}

  }
  public void testMyJSON2ReadMixedStreamed() throws IOException {
    //可使用
    //InputStream inputStream = new URL(“http://baidu.com”).openStream();
    //JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

    Gson gson = new Gson();
    StringReader stringReader = new StringReader(MyJSON2);
    JsonReader jsonReader = new JsonReader(stringReader);
    
    //jsonReader.beginObject();
    MyClass2 a = gson.fromJson(jsonReader, MyClass2.class);
    //jsonReader.endObject();
  }

  private static final String MyJSON = "{\"processGroups\":[{\"revision\":{\"version\":0},\"id\":\"f31bd3f8-dbfa-36fe-5f2f-c81856973b7a\",\"uri\":\"http://10.0.82.131:8080/nifi-api/process-groups/f31bd3f8-dbfa-36fe-5f2f-c81856973b7a\",\"position\":{\"x\":241.28508921730275,\"y\":-322.28694984759807},\"permissions\":{\"canRead\":true,\"canWrite\":true},\"bulletins\":[],\"component\":{\"id\":\"f31bd3f8-dbfa-36fe-5f2f-c81856973b7a\",\"parentGroupId\":\"b92f9892-9ecf-31a2-d86b-dc2c8107ec7a\",\"position\":{\"x\":241.28508921730275,\"y\":-322.28694984759807},\"name\":\"searchArticle2\",\"comments\":\"\",\"runningCount\":0,\"stoppedCount\":10,\"invalidCount\":0,\"disabledCount\":0,\"activeRemotePortCount\":0,\"inactiveRemotePortCount\":0,\"inputPortCount\":1,\"outputPortCount\":0},\"status\":{\"id\":\"f31bd3f8-dbfa-36fe-5f2f-c81856973b7a\",\"name\":\"searchArticle2\",\"statsLastRefreshed\":\"16:46:02 CST\",\"aggregateSnapshot\":{\"id\":\"f31bd3f8-dbfa-36fe-5f2f-c81856973b7a\",\"name\":\"searchArticle2\",\"flowFilesIn\":0,\"bytesIn\":0,\"input\":\"0 (0 bytes)\",\"flowFilesQueued\":0,\"bytesQueued\":0,\"queued\":\"0 (0 bytes)\",\"queuedCount\":\"0\",\"queuedSize\":\"0 bytes\",\"bytesRead\":0,\"read\":\"0 bytes\",\"bytesWritten\":0,\"written\":\"0 bytes\",\"flowFilesOut\":0,\"bytesOut\":0,\"output\":\"0 (0 bytes)\",\"flowFilesTransferred\":0,\"bytesTransferred\":0,\"transferred\":\"0 (0 bytes)\",\"bytesReceived\":0,\"flowFilesReceived\":0,\"received\":\"0 (0 bytes)\",\"bytesSent\":0,\"flowFilesSent\":0,\"sent\":\"0 (0 bytes)\",\"activeThreadCount\":0}},\"runningCount\":0,\"stoppedCount\":10,\"invalidCount\":0,\"disabledCount\":0,\"activeRemotePortCount\":0,\"inactiveRemotePortCount\":0,\"inputPortCount\":1,\"outputPortCount\":0},{\"revision\":{\"version\":0},\"id\":\"a321925a-0b33-3908-a742-daede3d60376\",\"uri\":\"http://10.0.82.131:8080/nifi-api/process-groups/a321925a-0b33-3908-a742-daede3d60376\",\"position\":{\"x\":722.5158021079278,\"y\":-323.05413124408244},\"permissions\":{\"canRead\":true,\"canWrite\":true},\"bulletins\":[],\"component\":{\"id\":\"a321925a-0b33-3908-a742-daede3d60376\",\"parentGroupId\":\"b92f9892-9ecf-31a2-d86b-dc2c8107ec7a\",\"position\":{\"x\":722.5158021079278,\"y\":-323.05413124408244},\"name\":\"searchArticle3\",\"comments\":\"\",\"runningCount\":0,\"stoppedCount\":10,\"invalidCount\":0,\"disabledCount\":0,\"activeRemotePortCount\":0,\"inactiveRemotePortCount\":0,\"inputPortCount\":1,\"outputPortCount\":0},\"status\":{\"id\":\"a321925a-0b33-3908-a742-daede3d60376\",\"name\":\"searchArticle3\",\"statsLastRefreshed\":\"16:46:02 CST\",\"aggregateSnapshot\":{\"id\":\"a321925a-0b33-3908-a742-daede3d60376\",\"name\":\"searchArticle3\",\"flowFilesIn\":0,\"bytesIn\":0,\"input\":\"0 (0 bytes)\",\"flowFilesQueued\":0,\"bytesQueued\":0,\"queued\":\"0 (0 bytes)\",\"queuedCount\":\"0\",\"queuedSize\":\"0 bytes\",\"bytesRead\":0,\"read\":\"0 bytes\",\"bytesWritten\":0,\"written\":\"0 bytes\",\"flowFilesOut\":0,\"bytesOut\":0,\"output\":\"0 (0 bytes)\",\"flowFilesTransferred\":0,\"bytesTransferred\":0,\"transferred\":\"0 (0 bytes)\",\"bytesReceived\":0,\"flowFilesReceived\":0,\"received\":\"0 (0 bytes)\",\"bytesSent\":0,\"flowFilesSent\":0,\"sent\":\"0 (0 bytes)\",\"activeThreadCount\":0}},\"runningCount\":0,\"stoppedCount\":10,\"invalidCount\":0,\"disabledCount\":0,\"activeRemotePortCount\":0,\"inactiveRemotePortCount\":0,\"inputPortCount\":1,\"outputPortCount\":0},{\"revision\":{\"version\":0},\"id\":\"982063a0-529b-30a9-74dd-21b615dd90d4\",\"uri\":\"http://10.0.82.131:8080/nifi-api/process-groups/982063a0-529b-30a9-74dd-21b615dd90d4\",\"position\":{\"x\":-232.3669722450996,\"y\":-326.0669181093168},\"permissions\":{\"canRead\":true,\"canWrite\":true},\"bulletins\":[],\"component\":{\"id\":\"982063a0-529b-30a9-74dd-21b615dd90d4\",\"parentGroupId\":\"b92f9892-9ecf-31a2-d86b-dc2c8107ec7a\",\"position\":{\"x\":-232.3669722450996,\"y\":-326.0669181093168},\"name\":\"searchArticle\",\"comments\":\"\",\"runningCount\":0,\"stoppedCount\":10,\"invalidCount\":0,\"disabledCount\":0,\"activeRemotePortCount\":0,\"inactiveRemotePortCount\":0,\"inputPortCount\":1,\"outputPortCount\":0},\"status\":{\"id\":\"982063a0-529b-30a9-74dd-21b615dd90d4\",\"name\":\"searchArticle\",\"statsLastRefreshed\":\"16:46:02 CST\",\"aggregateSnapshot\":{\"id\":\"982063a0-529b-30a9-74dd-21b615dd90d4\",\"name\":\"searchArticle\",\"flowFilesIn\":0,\"bytesIn\":0,\"input\":\"0 (0 bytes)\",\"flowFilesQueued\":0,\"bytesQueued\":0,\"queued\":\"0 (0 bytes)\",\"queuedCount\":\"0\",\"queuedSize\":\"0 bytes\",\"bytesRead\":0,\"read\":\"0 bytes\",\"bytesWritten\":0,\"written\":\"0 bytes\",\"flowFilesOut\":0,\"bytesOut\":0,\"output\":\"0 (0 bytes)\",\"flowFilesTransferred\":0,\"bytesTransferred\":0,\"transferred\":\"0 (0 bytes)\",\"bytesReceived\":0,\"flowFilesReceived\":0,\"received\":\"0 (0 bytes)\",\"bytesSent\":0,\"flowFilesSent\":0,\"sent\":\"0 (0 bytes)\",\"activeThreadCount\":0}},\"runningCount\":0,\"stoppedCount\":10,\"invalidCount\":0,\"disabledCount\":0,\"activeRemotePortCount\":0,\"inactiveRemotePortCount\":0,\"inputPortCount\":1,\"outputPortCount\":0}]}";
  static final class MyClass {
    ArrayList<Object> processGroups;

    MyClass(ArrayList<Object> processGroups) {
      this.processGroups = processGroups;
    }

    // used by Gson
    MyClass() {}

  }
  public void testMyJSONReadMixedStreamed() throws IOException {
    Gson gson = new Gson();
    StringReader stringReader = new StringReader(MyJSON);
    JsonReader jsonReader = new JsonReader(stringReader);

    //jsonReader.beginObject();
    MyClass a = gson.fromJson(jsonReader, MyClass.class);
    //jsonReader.endObject();
  }

  public void testReadMixedStreamed() throws IOException {
    Gson gson = new Gson();
    StringReader stringReader = new StringReader(CARS_JSON);
    JsonReader jsonReader = new JsonReader(stringReader);

    jsonReader.beginArray();
    assertEquals(BLUE_MUSTANG, gson.fromJson(jsonReader, Car.class));//按照Car.class读取
    assertEquals(BLACK_BMW, gson.fromJson(jsonReader, Car.class));
    assertEquals(RED_MIATA, gson.fromJson(jsonReader, Car.class));
    jsonReader.endArray();
  }

  public void testReaderDoesNotMutateState() throws IOException {
    Gson gson = new Gson();
    JsonReader jsonReader = new JsonReader(new StringReader(CARS_JSON));
    jsonReader.beginArray();

    jsonReader.setLenient(false);
    gson.fromJson(jsonReader, Car.class);
    assertFalse(jsonReader.isLenient());

    jsonReader.setLenient(true);
    gson.fromJson(jsonReader, Car.class);
    assertTrue(jsonReader.isLenient());
  }

  public void testWriteDoesNotMutateState() throws IOException {
    Gson gson = new Gson();
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());
    jsonWriter.beginArray();

    jsonWriter.setHtmlSafe(true);
    jsonWriter.setLenient(true);
    gson.toJson(BLUE_MUSTANG, Car.class, jsonWriter);
    assertTrue(jsonWriter.isHtmlSafe());
    assertTrue(jsonWriter.isLenient());

    jsonWriter.setHtmlSafe(false);
    jsonWriter.setLenient(false);
    gson.toJson(BLUE_MUSTANG, Car.class, jsonWriter);
    assertFalse(jsonWriter.isHtmlSafe());
    assertFalse(jsonWriter.isLenient());
  }

  public void testReadInvalidState() throws IOException {
    Gson gson = new Gson();
    JsonReader jsonReader = new JsonReader(new StringReader(CARS_JSON));
    jsonReader.beginArray();
    jsonReader.beginObject();
    try {
      gson.fromJson(jsonReader, String.class);
      fail();
    } catch (JsonParseException expected) {
    }
  }

  public void testReadClosed() throws IOException {
    Gson gson = new Gson();
    JsonReader jsonReader = new JsonReader(new StringReader(CARS_JSON));
    jsonReader.close();
    try {
      gson.fromJson(jsonReader, new TypeToken<List<Car>>() {}.getType());
      fail();
    } catch (JsonParseException expected) {
    }
  }

  public void testWriteInvalidState() throws IOException {
    Gson gson = new Gson();
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());
    jsonWriter.beginObject();
    try {
      gson.toJson(BLUE_MUSTANG, Car.class, jsonWriter);
      fail();
    } catch (IllegalStateException expected) {
    }
  }

  public void testWriteClosed() throws IOException {
    Gson gson = new Gson();
    JsonWriter jsonWriter = new JsonWriter(new StringWriter());
    jsonWriter.beginArray();
    jsonWriter.endArray();
    jsonWriter.close();
    try {
      gson.toJson(BLUE_MUSTANG, Car.class, jsonWriter);
      fail();
    } catch (IllegalStateException expected) {
    }
  }

  public void testWriteNulls() {
    Gson gson = new Gson();
    try {
      gson.toJson(new JsonPrimitive("hello"), (JsonWriter) null);
      fail();
    } catch (NullPointerException expected) {
    }

    StringWriter stringWriter = new StringWriter();
    gson.toJson(null, new JsonWriter(stringWriter));
    assertEquals("null", stringWriter.toString());
  }

  public void testReadNulls() {
    Gson gson = new Gson();
    try {
      gson.fromJson((JsonReader) null, Integer.class);
      fail();
    } catch (NullPointerException expected) {
    }
    try {
      gson.fromJson(new JsonReader(new StringReader("true")), null);
      fail();
    } catch (NullPointerException expected) {
    }
  }

  public void testWriteHtmlSafe() {
    List<String> contents = Arrays.asList("<", ">", "&", "=", "'");
    Type type = new TypeToken<List<String>>() {}.getType();

    StringWriter writer = new StringWriter();
    new Gson().toJson(contents, type, new JsonWriter(writer));
    assertEquals("[\"\\u003c\",\"\\u003e\",\"\\u0026\",\"\\u003d\",\"\\u0027\"]",
        writer.toString());

    writer = new StringWriter();
    new GsonBuilder().disableHtmlEscaping().create()
        .toJson(contents, type, new JsonWriter(writer));
    assertEquals("[\"<\",\">\",\"&\",\"=\",\"'\"]",
        writer.toString());
  }

  public void testWriteLenient() {
    List<Double> doubles = Arrays.asList(Double.NaN, Double.NEGATIVE_INFINITY,
        Double.POSITIVE_INFINITY, -0.0d, 0.5d, 0.0d);
    Type type = new TypeToken<List<Double>>() {}.getType();

    StringWriter writer = new StringWriter();
    JsonWriter jsonWriter = new JsonWriter(writer);
    new GsonBuilder().serializeSpecialFloatingPointValues().create()
        .toJson(doubles, type, jsonWriter);
    assertEquals("[NaN,-Infinity,Infinity,-0.0,0.5,0.0]", writer.toString());

    try {
      new Gson().toJson(doubles, type, new JsonWriter(new StringWriter()));
      fail();
    } catch (IllegalArgumentException expected) {
    }
  }

  static final class Car {
    String name;
    int color;

    Car(String name, int color) {
      this.name = name;
      this.color = color;
    }

    // used by Gson
    Car() {}

    @Override public int hashCode() {
      return name.hashCode() ^ color;
    }

    @Override public boolean equals(Object o) {
      return o instanceof Car
          && ((Car) o).name.equals(name)
          && ((Car) o).color == color;
    }
  }
}
