import React from 'react';

export default function ShowSearchResult({ searchReclamationResult }) {
    return (
        searchReclamationResult.map(el => {
            return (
                <div>
                    Reclamation Description: {el.reclamationDescription.value}
                    Reclamation Value (uri):  <a href={el.reclamation.value}>{el.reclamation.value}</a>
                </div>
            );
        })
    );
}
