import React from "react";

export default function DisplayAllOrdersAndPayements({allOrderAndPayements}) {
    console.log(allOrderAndPayements)
    return (
        allOrderAndPayements.map((item, i) => {
            return (
                <div key={item.NumCommande.value}>
                    <h1>NumCommande: {item.NumCommande.value}</h1>
                    <h2>Montant: {item.montant.value}</h2>
                    <h2>Produits:</h2>
                    <ul>
                        <li>
                            <h3>Nom:</h3>
                            {item.Nom.value}
                        </li>
                        <li>
                            <h3>Description</h3>
                            {item.Description.value}
                        </li>
                    </ul>
                </div>
            );
        })
    );
}