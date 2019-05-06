This code was written as part of a Numerical Dynamics Simulations class at Tulane University.

A Delaunay triangulation is a triangulation of a point set such that no point in the set lies in the circumcircle of any triangle in the triangulation. Delaunay triangulations have properties which make them good candidates for use as interpolation grids. The Delaunay triangulation can be constructed for point sets in 2, 3, or any higher dimensional space.

Delaunay triangulations can be used for:
 - Image compression
 - Facial warping (Snapchat filters)
 - Finite Element Analysis
 - Discrete Function Interpolation
 - Terrain Modelling

Relevant topics:
 - Bowyer-Watson Algorithm
 - Graham Scan Algorithm
 - Barycentric Coordinates
 - Barycentric Interpolation
 - Electric Fields

The code in this repository demonstrates how Delaunay triangulations can be used as interpolation grids in 2 dimensional space. The code in DelaunayDemo.ipynb generates a random set of points, P, in 2 dimensional space. Then, these points are triangulated using scipy's Delaunay triangulation capabilities. A point charge (a particle with an electric charge) is placed at the center of the triangulated points. An electric field with magnitude E results at each of the points in P. The value of E is calculated at each point in P using the euclidean distance formula and the equation which gives E as a function of distance from the point charge. Note: The value of E differs for each point in P because each point is at a different distance from the central point charge; there is an inverse square relationship between E and r, where r is the distance of a point from the point charge. Then, random test points are generated and the value of E at these test points is determined through barycentric interpolation using the values of E at each point in P. Because E is not a linear function with respect to r, barycentric interpolation is not optimal, but works for the purpose of demonstration. The error in the predicted values of E at the test points is plotted. It should be noted that there is less error in the predicted value of E for test points that are further from the point charge because the function being interpolated for becomes more linear for larger values of r. 
