import React from "react";

export default function Displaybrand({ allBrand }) {
  return allBrand.map((brand, i) => {
    return (
      <div key={i}>
        <h1>brand </h1>

        <div>Nom {brand.Brand_name.value}</div>
      </div>
    );
  });
}
