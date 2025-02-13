export class CalculService {
    static async sendCalcul(calcul){
        const response = await fetch("/polycalculator/calcul/"+calcul, {method: "POST"});
        if(response.status === 200){
            return response.json();
        }
        else{
            return false;
        }
    }

    static async getResult(id){
        const response = await fetch("/polycalculator/id/"+id);
        if(response.status === 200){
            return response.json();
        }
        else{
            return null;
        }
    }
}