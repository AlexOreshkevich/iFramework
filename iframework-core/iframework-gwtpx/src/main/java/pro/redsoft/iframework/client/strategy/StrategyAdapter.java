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
package pro.redsoft.iframework.client.strategy;

/**
 * StrategyAdapter. <br/>
 * Godlike dual generic-based strategy-adapter patterns (GoF) implementation
 * 
 * @author alex oreshkevich
 */
@SuppressWarnings("unchecked")
public class StrategyAdapter {

  /**
   * StrategyImplException.
   * 
   * @author Alex N. Oreshkevich
   */
  @SuppressWarnings("serial")
  public static class StrategyImplException extends Exception {

    public StrategyImplException(String string) {
      super(string);
    }

    public StrategyImplException(String string, Exception e) {
      super(string, e);
    }
  }

  /* ****** * * ENTRY POINT METHODS * * ******** */

  private static <T extends Object> boolean checkTypes(T bean, Strategy<T> strategy)
      throws StrategyImplException {

    if (bean == null) {
      throw new StrategyImplException("CheckTypes NULL bean access");
    }

    if (strategy == null) {
      throw new StrategyImplException("CheckTypes NULL strategy access");
    }

    if (strategy.getBeanType() == null) {
      throw new StrategyImplException("Strategy " + strategy.getClass().getName()
          + " defines NULL bean type");
    }

    return bean.getClass() == strategy.getBeanType();
  }

  private static <T extends Object> void exec(T bean, ComplexStrategy complexStrategy)
      throws StrategyImplException {

    if (null == complexStrategy || null == bean) {
      throw new NullPointerException((null == bean) + " " + (null == complexStrategy) + " ");
    }

    // container-based search
    if (complexStrategy instanceof MappedComplexStrategy) {

      MappedComplexStrategy str = (MappedComplexStrategy) complexStrategy;

      try {
        ((Strategy<T>) str.getStrategies().get(bean.getClass())).doAction(bean);
      }
      catch (Exception e) {
        throw new IllegalArgumentException(e);
      }
    }
    // linear search
    else if (complexStrategy instanceof SetComplexStrategy) {

      for (Strategy<? extends Object> strategy : ((SetComplexStrategy) complexStrategy).getStrategys()) {

        Strategy<T> str;
        // ClassCastException catched, it's not true error for this
        try {
          str = (Strategy<T>) strategy;
          if (!checkTypes(bean, str)) {
            continue;
          }
        }
        catch (ClassCastException e) {
          continue;
        }

        // execution can throw errors
        Exception err = execImpl(bean, str);
        if (null != err) {
          throw new IllegalArgumentException(err);
        }
      }
    }
    else {
      throw new UnsupportedOperationException(complexStrategy.getClass().getName());
    }
  }

  private static <T extends Object> void exec(T bean, Strategy<T> strategy) throws StrategyImplException {

    if (!checkTypes(bean, strategy)) {
      throw new IllegalArgumentException();
    }

    execImpl(bean, strategy);
  }

  /* ******************************************** */

  private static <T extends Object> ClassCastException execImpl(T bean, Strategy<T> strategy)
      throws StrategyImplException {
    try {
      strategy.doAction(bean);
    }
    catch (ClassCastException e) {
      return e;
    }
    catch (Exception e1) {
      throw new StrategyImplException("Strategy " + strategy.getClass().getName()
          + " doAction() error : \n" + e1.getMessage(), e1);
    }

    return null;
  }

  public static <T extends Object> void execute(Object bean, ComplexStrategy complexStrategy)
      throws StrategyImplException {
    exec((T) bean, complexStrategy);
  }

  public static <T extends Object> void execute(Object bean, HasStrategy strategyContainer)
      throws StrategyImplException {

    if (strategyContainer == null) {
      throw new IllegalArgumentException("Adapter::execute | Strategy Container is null");
    }

    execute(bean, strategyContainer.getStrategy());
  }

  public static <T extends Object> void execute(Object bean, Strategy<?> strategy)
      throws StrategyImplException {
    exec((T) bean, (Strategy<T>) strategy);
  }
}
