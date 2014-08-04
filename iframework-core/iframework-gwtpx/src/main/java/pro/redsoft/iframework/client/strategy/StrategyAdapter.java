/*
The MIT License (MIT)

Copyright (c) 2013 - 2014  REDSOFT.PRO

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
package pro.redsoft.iframework.client.strategy;

/**
 * Dual generic-based strategy-adapter patterns (GoF) implementation.
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
