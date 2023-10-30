import React from "react";

export default function Displayproduct({ allProduct }) {
  return allProduct.map((product, i) => {
    return (
      <div key={i}>
        <h1>Product </h1>

        <div>
          Nom {product.Nom.value}
          Description {product.Description.value}
          Prix {product.Prix.value}
        </div>
      </div>
    );
  });
}
