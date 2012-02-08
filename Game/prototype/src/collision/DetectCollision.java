/*
 * File Name: DetectCollision.java
 *
 * Author: Kiet Lam <ktklam9@gmail.com>
 *
 * Purpose: Game collision detection using
 *          jMonkeyEngine
 *
 * Date Created: 2/8/12
 * Date Modified: 2/8/12
 *
 */

package collision;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Spatial;
import com.jme3.bounding.*;
import com.jme3.system.AppSettings;
import com.jme3.collision.CollisionResults;

import javax.swing.JOptionPane;


// Extend SimpleApplication
public class DetectCollision extends SimpleApplication
{
    private BulletAppState bulletAppState;
    private Vector3f velocityV;
    private Spatial smallCube;
    private Spatial bigCube;

    public DetectCollision()
    {
        // Set the velocity vector
        velocityV = new Vector3f(0.05f, 0, 0);
    }

    public static void main(String[] args)
    {
        // Start our program
        DetectCollision dc = new DetectCollision();

        // Instantiate a settings
        AppSettings settings = new AppSettings(true);

        // Set max frame rate to 60fps
        settings.setFrameRate(60);

        settings.setTitle("Mesa Robotics!!!");
        dc.setSettings(settings);
        dc.start();
    }

    @Override
    public void simpleInitApp()
    {
        // Create our small cube
        Box box1 = new Box(new Vector3f(-3, 0, 0), 0.2f, 0.2f, 0.2f);
        smallCube = new Geometry("Box", box1);
        Material mat1 = new Material(assetManager,
            "Common/MatDefs/Misc/Unshaded.j3md");
        mat1.setColor("Color", ColorRGBA.Blue);
        smallCube.setMaterial(mat1);

        // Create our big cube
        Box box2 = new Box(new Vector3f(3, 0, 0), 1, 1, 1);
        bigCube = new Geometry("Box", box2);
        Material mat2 = new Material(assetManager,
            "Common/MatDefs/Misc/Unshaded.j3md");
        mat2.setColor("Color", ColorRGBA.Red);
        bigCube.setMaterial(mat2);

        // Make them both visible by attaching to the rootNode
        rootNode.attachChild(smallCube);
        rootNode.attachChild(bigCube);
    }

    // Our update loop
    @Override
    public void simpleUpdate(float tpf)
    {
        smallCube.move(velocityV);

        // Detect collision here:
        CollisionResults results = new CollisionResults();
        bigCube.collideWith(smallCube.getWorldBound(), results);

        // If there was a collision
        if (results.size() > 0)
        {
            // Display a popup box
            JOptionPane.showMessageDialog(null, "Collision detected!");
            System.exit(0);
        }
    }
}