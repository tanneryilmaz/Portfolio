import numpy as np
import matplotlib.pyplot as plt

def linreg(x, y, err_y, deg_of_freedom, xlabel, ylabel, plot_title):
    
    delta = sum(1/err_y**2) * sum(x**2/err_y**2) - (sum(x/err_y**2))**2
    m = (sum(1/err_y**2) * sum(x*y/err_y**2) - sum(x/err_y**2) * sum(y/err_y**2)) / delta
    b = (sum(x**2/err_y**2) * sum(y/err_y**2) - sum(x/err_y**2) * sum(x*y/err_y**2)) / delta

    err_m = np.sqrt(sum(1/err_y**2) / delta)
    err_b = np.sqrt(sum(x**2/err_y**2) / delta)
    fittemp = m*(x) + b

    #calculating chi-squared
    chisq = sum((y - fittemp)**2/err_y**2)
    reduced_chisq = chisq / deg_of_freedom
    
    #plotting data and line of best fit
    
    plt.errorbar(x, y, yerr = err_y, fmt = "bo", markersize = "5", linewidth=3)
    plt.ylabel(ylabel)
    plt.xlabel(xlabel)
    plt.suptitle(plot_title)

    plt.plot(x, m * x + b, 'r') #plotting line of best fit
    plt.savefig(plot_title + '.png')
    
    mf = str(m)
    err_mf = str(err_m)
    bf = str(b)
    err_bf = str(err_b)
    chisqf = str(chisq)
    reduced_chisqf = str(reduced_chisq)
    
    print("m = "+mf+" +/- "+err_mf+"\nb = "+bf+" +/- "+err_bf+"\nchi-squared = "+chisqf+"\nreduced chi-squared = "+reduced_chisqf)        
    
    return np.around([m, err_m, b, err_b, chisq, reduced_chisq], 4)