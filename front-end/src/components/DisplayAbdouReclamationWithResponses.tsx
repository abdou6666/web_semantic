import React from 'react';

export default function DisplayAbdouReclamationWithResponses({ allReclamationsAndResponses }) {
    return (
        allReclamationsAndResponses.map((el, i) => {
            return (
                <div key={i}>
                    <strong> Reclamation : </strong> {el.responseDescription.value} {' => '}
                    <strong> Response :  </strong>{el.reclamationDescription.value}
                </div>
            );
        })
    );
}
