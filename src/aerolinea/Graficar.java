/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aerolinea;

import edu.uci.ics.jung.algorithms.layout.FRLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import org.apache.commons.collections15.Transformer;

/**
 *
 * @author Inspiron
 */
public class Graficar {
    
    public void paintGraph(DirectedGraph X) {
       
        Layout<Viaje, Arista> layout = new FRLayout(X);
        layout.setSize(new Dimension(650, 650));
        BasicVisualizationServer<Viaje, Arista> vv =
                new BasicVisualizationServer<>(layout);
        vv.setPreferredSize(new Dimension(700, 700));
// Setup up a new vertex to paint transformer...
        Transformer<Viaje, Paint> vertexPaint = new Transformer<Viaje, Paint>() {
            @Override
            public Paint transform(Viaje i) {
                if (i.getID().equals("TGU")) {
                    return Color.CYAN;
                } else {
                    return Color.GREEN;
                }
            }
        };
        Transformer<Viaje, Shape> vertexSize = new Transformer<Viaje, Shape>() {
            public Shape transform(Viaje i) {
                Ellipse2D circle = new Ellipse2D.Double(-15, -15, 30, 30);
                // in this case, the vertex is twice as large
                return circle;
            }
        };
// Set up a new stroke Transformer for the edges
        float dash[] = {10.0f};
        final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
        Transformer<Arista, Stroke> edgeStrokeTransformer =
                new Transformer<Arista, Stroke>() {
                    public Stroke transform(Arista s) {
                        return edgeStroke;
                    }
                };
        vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
        vv.getRenderContext().setVertexShapeTransformer(vertexSize);

        vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.CNTR);
        JFrame frame = new JFrame("Ciudades");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);
    }
}
    