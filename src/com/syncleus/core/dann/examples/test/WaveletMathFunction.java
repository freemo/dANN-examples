package com.syncleus.core.dann.examples.test;


import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.TreeSet;

public class WaveletMathFunction extends MathFunction
{
    private TreeSet<String> dimensions = new TreeSet<String>();
    private ArrayList<WaveMultidimensionalMathFunction> waves = new ArrayList<WaveMultidimensionalMathFunction>();
    //private Hashtable<DistributedFormedWaveMathFunction, String[]> waves = new Hashtable<DistributedFormedWaveMathFunction, String>();
    //private Hashtable<String, WaveMultidimensionalMathFunction[]> waves = new Hashtable<String, WaveMultidimensionalMathFunction[]>();

    public WaveletMathFunction(String[] dimensions)
    {
        super(dimensions);
        for(String dimension:dimensions)
        {
            this.dimensions.add(dimension);
        }
    }



    public TreeSet<String> getDimensions()
    {
        return new TreeSet<String>(this.dimensions);
    }



    public void setDimension(String dimension, double value)
    {
        this.setParameter(this.getParameterNameIndex(dimension), value);
    }



    public double getDimension(String dimension)
    {
        return this.getParameter(this.getParameterNameIndex(dimension));
    }

    /*
    private boolean checkWave(WaveMultidimensionalMathFunction wave)
    {
    String[] waveDimensions = wave.getDimensionNames();
    for(String waveDimension : waveDimensions)
    if( this.dimensions.contains(waveDimension) == false )
    return false;
    return true;
    }*/

    /*
    private void addToWaveArray(WaveDimension newWave)
    {
    WaveMultidimensionalMathFunction[] currentWaves = this.waves.get(newWave.getDimension());
    WaveMultidimensionalMathFunction[] newWaves;
    if( currentWaves != null)
    {
    newWaves = new WaveMultidimensionalMathFunction[currentWaves.length + 1];
    System.arraycopy(newWaves, 0, currentWaves, 0, currentWaves.length);
    }
    else
    newWaves = new WaveMultidimensionalMathFunction[1];
    newWaves[newWaves.length - 1] = newWave.getWave();
    this.waves.put(newWave.getDimension(), newWaves);
    }*/

    public void addWave(WaveMultidimensionalMathFunction wave)
    {
        //if( checkWave(wave) == false )
        //    throw new InvalidParameterException("dimensions dont match");

        this.waves.add(wave);
    }



    public double calculate()
    {
        double waveTotal = 0.0;
        for(WaveMultidimensionalMathFunction currentWave:this.waves)
        {
            for(String dimension:this.dimensions)
            {
                try
                {
                    currentWave.setDimension(dimension, this.getDimension(dimension));
                }
                catch(InvalidParameterException caughtException)
                {
                }
            }

            waveTotal += currentWave.calculate();
        }

        return waveTotal;
    /*
    double result = 0.0;
    for(int index = 0; index < this.waves.size(); index++)
    {
    double waveProduct = 1.0;
    double waveTotal = 0.0;
    for(String dimension : this.dimensions)
    {
    WaveMultidimensionalMathFunction wave = this.waves.get(dimension)[index];
    //System.out.println("wave: " + wave);
    if( wave != null )
    {
    wave.setX(this.getParameter(this.getParameterNameIndex(dimension)));
    waveProduct *= wave.calculate();
    waveTotal += wave.calculate();
    }
    }
    if(waveTotal != 0.0 )
    result += waveProduct/waveTotal;
    }
    return result;*/

    /*
    double waveTotal = 0.0;
    for( DistributedFormedWaveMathFunction wave : this.waves.keySet())
    {
    String currentWaveDimension = this.waves.get(wave);
    int currentWaveDimensionIndex = this.getParameterNameIndex(currentWaveDimension);
    double dimensionValue = this.getParameter(currentWaveDimensionIndex);
    wave.setX(dimensionValue);
    waveTotal += wave.calculate();
    }
    return waveTotal;
     */
    }



    public WaveletMathFunction clone()
    {
        String[] dimensionsCopy = new String[this.dimensions.size()];
        this.dimensions.toArray(dimensionsCopy);

        WaveletMathFunction copy = new WaveletMathFunction(dimensionsCopy);

        for(WaveMultidimensionalMathFunction wave:this.waves)
        {
            copy.waves.add(wave);
        }

        return copy;
    /*
    for( Entry<String, WaveMultidimensionalMathFunction[]> wavePair : waves.entrySet())
    {
    //DistributedFormedWaveMathFunction wave = wavePair.getKey();
    //String dimension = wavePair.getValue();
    copy.waves.put(wavePair.getKey(), wavePair.getValue().clone());
    }
    return copy;*/
    }



    public String toString()
    {
        return "";
    }
}
