/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Monta
 */
public class Produit {
    int id;
    String codeproduit , des;
    int prixunitaire,Quantite,QuantiteM;

    public Produit(int id, String codeproduit, String des, int prixunitaire) {
        this.id = id;
        this.codeproduit = codeproduit;
        this.des = des;
        this.prixunitaire = prixunitaire;
    }

    public Produit(String codeproduit, String des, int Quantite) {
        this.codeproduit = codeproduit;
        this.des = des;
        this.Quantite = Quantite;
    }
    

    public Produit(String codeproduit, String des) {
        this.codeproduit = codeproduit;
        this.des = des;
    }

    public int getQuantiteM() {
        return QuantiteM;
    }

    public void setQuantiteM(int QuantiteM) {
        this.QuantiteM = QuantiteM;
    }

    public Produit() {
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int Quantite) {
        this.Quantite = Quantite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeproduit() {
        return codeproduit;
    }

    public void setCodeproduit(String codeproduit) {
        this.codeproduit = codeproduit;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(int prixunitaire) {
        this.prixunitaire = prixunitaire;
    }

    public void setPrixunitaire(String quantite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
