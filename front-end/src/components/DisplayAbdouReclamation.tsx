import React from 'react';

export default function DisplayAbdouReclamation({ allReclamation }) {
    return (
        allReclamation.map((reclamation, i) => {
            return (
                <div key={i}>
                    <h1>Reclamation {i + 1}</h1>
                    <div>
                        Description {reclamation.reclamationDescription.value}
                    </div>
                </div>
            );
        })
    );
}
