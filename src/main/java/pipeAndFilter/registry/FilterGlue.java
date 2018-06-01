package pipeAndFilter.registry;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import pipeAndFilter.Generator;
import pipeAndFilter.Processable;
import pipeAndFilter.SimpleFilter;
import pipeAndFilter.Sink;
import pipeAndFilter.impl.FilterSystem;
import pipeAndFilter.impl.GeneratorImpl;
import pipeAndFilter.impl.QueuePipe;
import pipeAndFilter.impl.SimpleFilterImpl;
import pipeAndFilter.impl.SinkImpl;

public class FilterGlue {

	

    public FilterSystem glue(List<Processable> processables) {
    	
    	if(processables == null || processables.size() < 2)
    		throw new IllegalArgumentException();
    	if(!isGenerator(processables.get(0)))
    		throw new IllegalArgumentException();
    	if(!isSink(processables.get(processables.size() - 1)))
    		throw new IllegalArgumentException();
    	
    	processables.subList(1, processables.size() - 1).forEach(p -> {if(!isFilter(p)) throw new IllegalArgumentException();});
    	
    	
    	try {
			generatePipes(processables);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	FilterSystem toRet = new FilterSystem();
    	toRet.addAllFilters(processables);
    	
    	return toRet;
    	
    }
    
    private void generatePipes(List<Processable> processables) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    	
    	int last = processables.size() - 1;
    	//List<SimpleFilter> filters = processables.stream().map(f -> (SimpleFilter)f).collect(Collectors.toList());
    	
    	QueuePipe lastPipe = null;
    	
    	for (int i = 0; i <= last; i++) {
			
    		if(i == 0) {
    			GeneratorImpl gen = (GeneratorImpl) processables.get(i);
    			lastPipe = new QueuePipe<>();
    			
    			Field pipefield = getField(gen.getClass(), "outputPipe");
    			pipefield.setAccessible(true);
    			pipefield.set(gen, lastPipe);
    		}
    		
    		else if(i == last) {

    			SinkImpl sink = (SinkImpl) processables.get(i);
    			
    			Field pipefield = getField(sink.getClass(), "inputPipe");
    			pipefield.setAccessible(true);
    			pipefield.set(sink, lastPipe);
    		
    		}
    		
    		else {

    			SimpleFilterImpl filter = (SimpleFilterImpl) processables.get(i);
    			Field inputPipefield = getField(filter.getClass(), "inputPipe");
    			Field outputPipefield = getField(filter.getClass(), "outputPipe");
    			
    			inputPipefield.setAccessible(true);
    			inputPipefield.set(filter, lastPipe);
    			
    			lastPipe = new QueuePipe<>();
    			
    			outputPipefield.setAccessible(true);
    			outputPipefield.set(filter, lastPipe);    			
    			
    		}
    		
    		
    		
		}
    	
    	
    }
    
    private Field getField(Class clazz, String fieldName) 
            throws NoSuchFieldException {
        try {
          return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
          Class superClass = clazz.getSuperclass();
          if (superClass == null) {
            throw e;
          } else {
            return getField(superClass, fieldName);
          }
        }
      }
    
    //TODO deberia ir esto aca?
    private boolean isGenerator(Processable p) {
    	return Generator.class.isAssignableFrom(p.getClass());
    }
    
    private boolean isSink(Processable p) {
    	return Sink.class.isAssignableFrom(p.getClass());
    }
    
    private boolean isFilter(Processable p) {
    	return SimpleFilter.class.isAssignableFrom(p.getClass());
    }
	
}
