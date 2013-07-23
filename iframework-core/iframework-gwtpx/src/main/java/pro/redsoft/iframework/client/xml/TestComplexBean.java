/**
 * Copyright 2013 REDSOFT.PRO
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pro.redsoft.iframework.client.xml;

/**
 * TestComplexBean.
 * 
 * @author Alex N. Oreshkevich
 */
public class TestComplexBean {

  // /*
  // * Core2 Class Open Library for Google Web Toolkit v2.1
  // *
  // * Copyright (C) 2010 Alexander Oreshkewitsh
  // *
  // * This program is free software; you can redistribute it and/or modify
  // * it under the terms of the GNU General Public License as published by
  // * the Free Software Foundation; either version 2 of the License, or
  // * (at your option) any later version.
  // *
  // * This program is distributed in the hope that it will be useful,
  // * but WITHOUT ANY WARRANTY; without even the implied warranty of
  // * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  // * GNU General Public License for more details.
  // *
  // * You should have received a copy of the GNU General Public License
  // * along with this program; if not, write to the Free Software
  // * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
  // *
  // * Author: Alexander Oreshkewitsh (aka Neo)
  // *
  // * Second Editor: Ignatovich Sergej
  // *
  // * E-Mail: neo.bsuir@gmail.com
  // *
  // */
  //
  // package ru.intertrust.cmj.client.turbo;
  //
  // import java.util.ArrayList;
  //
  // import by.ipps.egr.client.logic.StandardClassDescriptor;
  // import by.ipps.egr.client.modelr.JurRegBean;
  //
  //
  // /**
  // *
  // * @author Neo
  // *
  // */
  // @StandardClassDescriptor(author = "Neo", date = "12/10/10", currentRevision = 1, reviewers = { "" },
  // lastModified = "", lastModifiedBy = "")
  // public class TestComplexBean extends RBean {
  //
  // // Referrences for some bean, that extends RBean
  // RField<TestBean> bean1;
  // RField<TestBean> bean2;
  //
  // // some references too (with initializations)
  // RField<String> siski;
  // RField<Integer> capitalSize;
  //
  // RField<ArrayList<TestBean>> beanList;
  //
  // private RField<ArrayList<Integer>> array;
  // private RField<JurRegBean> jrb;
  //
  // // default constructor
  // public TestComplexBean() {
  // bean1 = createField("bean1", new TestBean(), DataTypes.tObject);
  // bean2 = createField("bean2", new TestBean(), DataTypes.tObject);
  // beanList = createField("beanList", new ArrayList<TestBean>(), DataTypes.tListObject);
  // siski = createField("NaimRusFull111", "НА РУССком  ");
  // capitalSize = createField("capitalSize222", -1);
  // jrb = createField("jrb", new JurRegBean(), DataTypes.tObject);
  // array = createField("array", new ArrayList<Integer>(), DataTypes.tListField);
  // }
  //
  // public JurRegBean getJrb() {
  // return jrb.getValue();
  // }
  //
  // public void setJrb(JurRegBean jrb) {
  // setField(this.jrb, jrb);
  // }
  //
  // /**
  // * @return the bean1
  // */
  // public TestBean getBean1() {
  // return bean1.getValue();
  // }
  //
  // /**
  // * @param bean1
  // * the bean1 to set
  // */
  // public void setBean1(TestBean bean1) {
  // setField(this.bean1, bean1);
  // }
  //
  // /**
  // * @return the bean2
  // */
  // public TestBean getBean2() {
  // return bean2.getValue();
  // }
  //
  // /**
  // * @param bean2
  // * the bean2 to set
  // */
  // public void setBean2(TestBean bean2) {
  // setField(this.bean2, bean2);
  // }
  //
  // /**
  // * @return the siski
  // */
  // public String getSiski() {
  // return siski.getValue();
  // }
  //
  // /**
  // * @param siski
  // * the siski to set
  // */
  // public void setSiski(String siski) {
  // setField(this.siski, siski);
  // }
  //
  // /**
  // * @return the capitalSize
  // */
  // public Integer getCapitalSize() {
  // return capitalSize.getValue();
  // }
  //
  // /**
  // * @param capitalSize
  // * the capitalSize to set
  // */
  // public void setCapitalSize(Integer capitalSize) {
  // setField(this.capitalSize, capitalSize);
  // }
  //
  // /**
  // * @return the beanList
  // */
  // public ArrayList<TestBean> getBeanList() {
  // return beanList.getValue();
  // }
  //
  // /**
  // * @param beanList
  // * the beanList to set
  // */
  // public void setBeanList(ArrayList<TestBean> beanList) {
  // setField(this.beanList, beanList);
  // }
  //
  // /**
  // * @return the array
  // */
  // public ArrayList<Integer> getArray() {
  // return array.getValue();
  // }
  //
  // /**
  // * @param array
  // * the array to set
  // */
  // public void setArray(ArrayList<Integer> array) {
  // setField(this.array, array);
  // }
  //
  // @Override
  // public CreatableBean newInstance() {
  // return new TestComplexBean();
  // }
  // }
}
