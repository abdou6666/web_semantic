import React from 'react';

export default function DisplayAllReclamationsAndResponsesWithClientAndAdminData({ allReclamationsAndResponsesWithClientAndAdminData }) {
    return (

        allReclamationsAndResponsesWithClientAndAdminData?.map(el => {
            console.log(el);
            return (
                <div style={{ marginBottom: '20px' }}>
                    <div>
                        <h3>Reclamation Details</h3>
                        <h4>Username :  {el.clientName.value}</h4>
                        <h5>Recalamation Description :  {el.reclamationDescription.value}</h5>
                    </div>
                    <div>
                        <h3>Response Details</h3>
                        <h4>admin name :  {el.adminName.value}</h4>
                        <h5>Response Description :  {el.responseDescription.value}</h5>
                    </div>
                </div>
            );
        })

    );
}
